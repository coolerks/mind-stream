package top.integer.blog.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.integer.blog.enums.Permission;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.AccountInfoMapper;
import top.integer.blog.mapper.AccountUserMapper;
import top.integer.blog.model.def.AccountInfoDef;
import top.integer.blog.model.def.AccountUserDef;
import top.integer.blog.model.dto.account.AccountAddDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.dto.account.LoginDto;
import top.integer.blog.model.dto.update.AccountUpdateDto;
import top.integer.blog.model.entity.AccountInfo;
import top.integer.blog.model.entity.AccountUser;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.account.info.AccountDetailVo;
import top.integer.blog.model.vo.account.info.AccountItemVo;
import top.integer.blog.operation.AccountEnsureOperation;
import top.integer.blog.operation.RolePermissionOperation;
import top.integer.blog.operation.UserRoleOperation;
import top.integer.blog.service.AccountService;
import top.integer.blog.utils.BeanUtil;
import top.integer.blog.utils.IpUtils;
import top.integer.blog.utils.JwtUtils;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class AccountServiceImpl implements AccountService {
    private final AccountUserMapper mapper;
    private final AccountInfoMapper accountInfoMapper;
    private final AccountEnsureOperation accountEnsureOperation;
    private final MD5 md5 = MD5.create();
    private final String salt = "fZTNSWajDQFJ8qDsI4DWhkYSD4U^ryK";

    @Override
    public PageVo<AccountItemVo> pageAccount(CommonPageQueryDto dto) {
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        AccountInfoDef i = AccountInfoDef.ACCOUNT_INFO;
        String keyword = dto.getKeyword();
        Integer status = dto.getStatus();
        QueryWrapper wrapper = QueryWrapper.create()
                .select(u.ID, u.USERNAME, u.EMAIL, i.NICKNAME, u.STATUS, u.LAST_LOGIN_TIME, i.AVATAR)
                .from(u)
                .join(i).on(u.ID.eq(i.ID))
                .where(
                        u.USERNAME.eq(keyword, StringUtils.isNotBlank(keyword))
                                .or(u.EMAIL.eq(keyword, StringUtils.isNotBlank(keyword)))
                                .and(u.STATUS.eq(status, status != null))
                );
        Page<AccountItemVo> page = mapper.paginateAs(new Page<>(dto.getPageNumber(), dto.getPageSize()),
                wrapper, AccountItemVo.class);
        return PageVo.of(page);
    }

    @Override
    public AccountDetailVo getAccountById(Long id) {
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        AccountInfoDef i = AccountInfoDef.ACCOUNT_INFO;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(
                        u.ID, i.NICKNAME, i.AVATAR, i.GENDER, i.SIGN, u.CREATE_TIME, i.UPDATE_TIME, u.EMAIL,
                        u.USERNAME, u.LOGIN_TIMES, u.STATUS, u.LAST_LOGIN_TIME
                ).select("INET_NTOA(last_login_ip) last_login_ip")
                .select("INET_NTOA(create_ip) create_ip")
                .from(u)
                .join(i).on(u.ID.eq(i.ID))
                .where(u.ID.eq(id));
        return mapper.selectOneByQueryAs(wrapper, AccountDetailVo.class);
    }

    @Override
    @Transactional
    public void addAccount(AccountAddDto dto) {
        Long userId = UserUtils.getUserId();
        long ip = IpUtils.getIpLong();

        String email = dto.getEmail();
        String username = dto.getUsername();

        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        QueryWrapper queryWrapper = QueryWrapper.create();
        queryWrapper.from(u)
                .where(u.EMAIL.eq(email)
                        .or(u.USERNAME.eq(username))
                );
        long count = mapper.selectCountByQuery(queryWrapper);
        if (count != 0) {
            throw new DataException("用户名或者邮箱重复");
        }

        String password = md5.digestHex(dto.getPassword() + salt);

        AccountUser accountUser = AccountUser.builder()
                .createBy(userId)
                .createIp(ip)
                .build();
        BeanUtils.copyProperties(dto, accountUser);
        accountUser.setPassword(password);
        this.mapper.insertSelective(accountUser);

        AccountInfo accountInfo = AccountInfo.builder()
                .id(accountUser.getId())
                .build();
        BeanUtils.copyProperties(dto, accountInfo);
        this.accountInfoMapper.insertSelective(accountInfo);
    }

    @Override
    public void updateAccount(AccountUpdateDto dto) {
        AccountInfo accountInfo = BeanUtil.copy(dto, new AccountInfo());
        AccountUser accountUser = BeanUtil.copy(dto, new AccountUser());

        accountEnsureOperation.ensureExistSuperAdmin(dto.getId());

        mapper.update(accountUser);
        accountInfoMapper.update(accountInfo);
    }

    @Override
    public String login(LoginDto dto) {
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        String password = md5.digestHex(dto.getPassword() + salt);

        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(u.ID, u.LOGIN_TIMES)
                .from(u)
                .where(u.EMAIL.eq(dto.getUsername()).or(u.USERNAME.eq(dto.getUsername())))
                .and(u.PASSWORD.eq(password));

        AccountUser accountUser = mapper.selectOneByQuery(queryWrapper);
        if (accountUser == null) {
            throw new DataException("登录失败，用户名或密码不正确");
        }

        mapper.update(AccountUser.builder()
                .id(accountUser.getId())
                .loginTimes(accountUser.getLoginTimes() + 1)
                .lastLoginIp(IpUtils.getIpLong())
                .lastLoginTime(LocalDateTime.now())
                .build()
        );
        return JwtUtils.createToken(accountUser.getId());
    }

    @Override
    public Set<Long> existAccount(List<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptySet();
        }
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        List<Long> list = ids.stream().distinct().toList();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(u.ID)
                .from(u)
                .where(u.ID.in(list));
        return mapper.selectListByQuery(queryWrapper).stream().map(AccountUser::getId).collect(Collectors.toSet());
    }

}
