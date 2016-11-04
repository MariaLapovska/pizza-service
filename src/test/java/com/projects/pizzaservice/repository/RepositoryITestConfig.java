package com.projects.pizzaservice.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mariia Lapovska
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:repoContextForH2.xml"})
public class RepositoryITestConfig extends AbstractTransactionalJUnit4SpringContextTests {
}