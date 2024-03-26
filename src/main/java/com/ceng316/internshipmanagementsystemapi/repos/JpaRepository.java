package com.ceng316.internshipmanagementsystemapi.repos;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JpaRepository<T,ID> extends ListCrudRepository<T,ID>, ListPagingAndSortingRepository<T,ID>, QueryByExampleExecutor<T> {


}