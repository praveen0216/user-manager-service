package com.management.user.input.mapper;

import com.management.user.model.Auction;
import com.management.user.output.repository.entity.AuctionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuctionMapper {

    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    AuctionEntity modelToEntity(Auction auction);

    Auction entityToModel(AuctionEntity auctionModel);

    List<AuctionEntity>  modelsToEntities(List<Auction> auctions);

    List<Auction>  entitiesToModels(List<AuctionEntity> auctions);
}
