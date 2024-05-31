package com.management.user.input.mapper;

import com.management.user.model.Bid;
import com.management.user.output.repository.entity.BidEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BidMapper {

    BidMapper INSTANCE = Mappers.getMapper(BidMapper.class);

    @Mapping(source = "auctionId", target = "auction.id")
    @Mapping(source = "participantId", target = "participant.id")
    BidEntity modelToEntity(Bid bid);

    @Mapping(source = "auction.id", target = "auctionId")
    @Mapping(source = "participant.id", target = "participantId")
    Bid entityToModel(BidEntity bidModel);

    List<Bid> entitiesToModel(List<BidEntity> bids);
    List<BidEntity> modelsToEntities(List<Bid> bids);
}