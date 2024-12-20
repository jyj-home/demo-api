package demo.api.service;

import demo.api.ChainedTransactional;
import demo.api.Ds1Transactional;
import demo.api.Ds2Transactional;
import demo.api.gen.entity.Person;
import demo.api.gen.entity.PersonKey;
import demo.api.gen.repository.PersonMapper;
import demo.api.gen2.entity.Users;
import demo.api.gen2.repository.UsersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyDbTestService {

  private final PersonMapper personMapper;
  private final UsersMapper usersMapper;

  public MyDbTestService(PersonMapper personMapper, UsersMapper usersMapper) {
    this.personMapper = personMapper;
    this.usersMapper = usersMapper;
  }

  // 操作第一个数据源并使用其对应的事务管理器
//  @Transactional(transactionManager = "ds1TransactionManager", rollbackFor = Exception.class)
  @Transactional(rollbackFor = Exception.class)
  public Person operateDs1() {
    PersonKey personKey = new PersonKey();

    personKey.setPersonId("001");
    personKey.setName("小李");

    Person person = personMapper.selectByPrimaryKey(personKey);
    System.out.println(person.getPersonId() + person.getName());
    return person;
  }

  // 操作第二个数据源并使用其对应的事务管理器
//  @Transactional(transactionManager = "ds2TransactionManager", rollbackFor = Exception.class)
  @Transactional(rollbackFor = Exception.class)
  public Users operateDs2() {
    Users users = usersMapper.selectByPrimaryKey(1);
    System.out.println(users.getId() + users.getName());
    return users;
  }

  // 同时操作两个数据源，保证事务的一致性（全部成功提交，有一个失败全部回滚）
  @Transactional(rollbackFor = Exception.class)
  public void operateBoth() {
    Person person = operateDs1();
    Users users = operateDs2();
    // 这里可以添加更多业务逻辑，对获取到的数据进行处理等
  }

  @Ds1Transactional
  public void operateCreateDs1() {
    Person person = new Person();
    person.setPersonId("001");
    person.setName("小李");
    person.setAge(103);

    int cnt = personMapper.updateByPrimaryKeySelective(person);
    System.out.println(cnt);
//    return cnt;
  }

  @Ds2Transactional
  public void operateCreateDs2() {
    Users users = new Users();
    users.setId(1);
    users.setName("jj");
    int cnt = usersMapper.updateByPrimaryKeySelective(users);
    System.out.println(cnt);
  }

  @ChainedTransactional
  public void operateCreateBoth() throws Exception {
    operateCreateDs2();
    operateCreateDs1();
//    throw new RuntimeException("xxx");
    // 这里可以添加更多业务逻辑，对获取到的数据进行处理等
  }
}
