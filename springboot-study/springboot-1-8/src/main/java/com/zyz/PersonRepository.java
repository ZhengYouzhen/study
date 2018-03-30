package com.zyz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;

/**
 * @author 娃娃鱼
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Name> {


}