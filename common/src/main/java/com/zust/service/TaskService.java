package com.zust.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.zust.entity.Task;

 /**
 * 任务;(task)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2024-1-3
 */
public interface TaskService{
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Task queryById(String id);
    /** 
     * 分页查询
     *
     * @param task 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Task> paginQuery(Task task, PageRequest pageRequest);
    /** 
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task insert(Task task);
    /** 
     * 更新数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task update(Task task);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
}