package top.integer.blog.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.AccountInfoMapper;
import top.integer.blog.mapper.AccountUserMapper;
import top.integer.blog.model.def.AccountInfoDef;
import top.integer.blog.model.def.AccountUserDef;
import top.integer.blog.model.dto.account.AccountAddDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.entity.AccountInfo;
import top.integer.blog.model.entity.AccountUser;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.account.info.AccountItemVo;
import top.integer.blog.model.vo.account.info.AccountDetailVo;
import top.integer.blog.service.AccountService;
import top.integer.blog.utils.BeanUtil;
import top.integer.blog.utils.IpUtils;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountUserMapper mapper;
    private final AccountInfoMapper accountInfoMapper;

    @Override
    public PageVo<AccountItemVo> pageAccount(CommonPageQueryDto dto) {
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        AccountInfoDef i = AccountInfoDef.ACCOUNT_INFO;
        String keyword = dto.getKeyword();
        Integer status = dto.getStatus();
        QueryWrapper wrapper = QueryWrapper.create()
                .select(u.ID, u.USERNAME, u.EMAIL, i.NICKNAME, u.STATUS, u.LAST_LOGIN_TIME)
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
    public AccountDetailVo getAccountById(Integer id) {
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

        AccountUser accountUser = AccountUser.builder()
                .createBy(userId)
                .createIp(ip)
                .build();
        BeanUtils.copyProperties(dto, accountUser);
        this.mapper.insertSelective(accountUser);

        AccountInfo accountInfo = AccountInfo.builder()
                .id(accountUser.getId())
                .build();
        BeanUtils.copyProperties(dto, accountInfo);
        this.accountInfoMapper.insertSelective(accountInfo);
    }
}
