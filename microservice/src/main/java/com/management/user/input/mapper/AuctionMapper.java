package com.management.user.input.mapper;

import com.management.user.model.Auction;
import com.management.user.output.repository.entity.AuctionEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(uses = {UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuctionMapper {

    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    AuctionEntity modelToEntity(Auction auction, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    Auction entityToModel(AuctionEntity auctionModel, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    List<AuctionEntity>  modelsToEntities(List<Auction> auctions, @Context CycleAvoidingMappingContext context);

    default List<Auction>  entitiesToModels(List<? extends AuctionEntity> auctions, @Context CycleAvoidingMappingContext context) {
        if(auctions == null) {
            return Collections.emptyList();
        }
        return auctions.stream().map(auctionEntity -> entityToModel(auctionEntity, context)).collect(Collectors.toList());
    }
}
