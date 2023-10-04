package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.MenuMapper;
import top.integer.blog.model.Pair;
import top.integer.blog.model.def.MenuDef;
import top.integer.blog.model.def.RoleMenusDef;
import top.integer.blog.model.dto.RoleMenuBatchDto;
import top.integer.blog.model.entity.Menu;
import top.integer.blog.model.entity.RoleMenus;
import top.integer.blog.mapper.RoleMenusMapper;
import top.integer.blog.operation.MenuOperation;
import top.integer.blog.operation.RoleOperation;
import top.integer.blog.service.RoleMenusService;
import org.springframework.stereotype.Service;
import top.integer.blog.utils.IpUtils;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单-角色 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@RequiredArgsConstructor
public class RoleMenusServiceImpl extends ServiceImpl<RoleMenusMapper, RoleMenus> implements RoleMenusService {
    private final MenuMapper menuMapper;
    private final MenuOperation menuOperation;
    private final RoleOperation roleOperation;
    @Override
    public void addRoleMenu(RoleMenuBatchDto dto) {
        Long userId = UserUtils.getUserId();
        long ip = IpUtils.getIpLong();
        LocalDateTime now = LocalDateTime.now();
        Long roleId = dto.getRoleId();

        if (roleId == 1) {
            throw new DataException("不允许修改超级管理员的菜单");
        }

        if (dto.getMenuIds() == null || dto.getMenuIds().isEmpty()) {
            return;
        }

        if (!roleOperation.existRole(roleId)) {
            throw new DataException("角色不存在");
        }
        // 去重
        // 过滤不存在的菜单
        // 过滤已经添加过的菜单
        List<Long> distinctIds = dto.getMenuIds().stream().distinct().toList();
        Set<Long> existMenus = menuOperation.existMenus(distinctIds);
        Set<Long> addedMenus = new HashSet<>(this.roleMenuIds(roleId));
        List<RoleMenus> roleMenus = distinctIds.stream()
                .filter(existMenus::contains)
                .filter(it -> !addedMenus.contains(it))
                .map(it -> RoleMenus.builder()
                        .roleId(roleId)
                        .menuId(it)
                        .createBy(userId)
                        .createIp(ip)
                        .createTime(now)
                        .build()
                )
                .toList();
        this.mapper.insertBatch(roleMenus);
    }

    @Override
    public void removeRoleMenu(RoleMenuBatchDto dto) {
        Long roleId = dto.getRoleId();
        if (roleId == 1) {
            throw new DataException("不允许修改超级管理员的菜单");
        }

        List<Pair<Long, Long>> ids = dto.getMenuIds().stream().map(it -> new Pair<>(roleId, it)).toList();
        this.mapper.removeRoleMenu(ids);
    }

    @Override
    public List<Long> roleMenuIds(Long id) {
        MenuDef m = MenuDef.MENU;
        RoleMenusDef rm = RoleMenusDef.ROLE_MENUS;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(m.ID)
                .from(m)
                .join(rm)
                .on(m.ID.eq(rm.MENU_ID))
                .where(rm.ROLE_ID.eq(id));
        return this.menuMapper.selectListByQuery(wrapper).stream().map(Menu::getId).toList();
    }
}
