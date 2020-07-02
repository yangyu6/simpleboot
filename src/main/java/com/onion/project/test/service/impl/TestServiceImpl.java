package com.onion.project.test.service.impl;

import com.onion.project.test.entity.Test;
import com.onion.project.test.dao.TestMapper;
import com.onion.project.test.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yu
 * @since 2020-07-02
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
