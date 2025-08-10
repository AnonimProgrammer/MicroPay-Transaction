package com.docker.mstransaction.util;

import com.docker.mstransaction.model.entity.Transaction;
import com.docker.mstransaction.model.TransactionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionModel toModel(Transaction category);

    Transaction toEntity(TransactionModel model);
}
