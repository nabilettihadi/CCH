package com.cch.cyclingmanager.repository.impl;

import com.cch.cyclingmanager.entity.Result;
import com.cch.cyclingmanager.entity.ResultId;
import com.cch.cyclingmanager.repository.ResultRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ResultRepositoryImpl extends GenericRepositoryImpl<Result, ResultId> implements ResultRepository {
}