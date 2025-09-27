package com.transaction.util;

import com.transaction.model.entity.Transaction;
import com.transaction.model.TransactionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionModel toModel(Transaction category);

    Transaction toEntity(TransactionModel model);
}
