package com.management.user.service;

import com.management.user.input.mapper.AuctionMapper;
import com.management.user.input.mapper.CycleAvoidingMappingContext;
import com.management.user.model.Auction;
import com.management.user.model.Report;
import com.management.user.model.Status;
import com.management.user.model.UserFrequency;
import com.management.user.output.repository.entity.AuctionEntity;
import com.management.user.output.repository.service.AuctionRepositoryService;
import com.management.user.output.repository.service.UserRepositoryService;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepositoryService userRepositoryService;

    private final AuctionRepositoryService auctionRepositoryService;

    private final AuctionMapper auctionMapper;

    public AdminService(UserRepositoryService userRepositoryService, AuctionRepositoryService auctionRepositoryService, AuctionMapper auctionMapper) {
        this.userRepositoryService = userRepositoryService;
        this.auctionRepositoryService = auctionRepositoryService;
        this.auctionMapper = auctionMapper;
    }

    public byte[] generateReport() {
        Report report = getReportDetails();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
            writeObjectsToCsv(report, outputStreamWriter);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate report", e);
        }
    }

    private Report getReportDetails() {
        Report report = new Report();
        List<UserFrequency> userBidCounts = userRepositoryService.findUserBidCounts();
        List<UserFrequency> userAuctionCountDTOS = userRepositoryService.findUserAuctionCounts();
        List<AuctionEntity> all = auctionRepositoryService.findAll();
        List<Auction> auctions = auctionMapper.entitiesToModels(all, new CycleAvoidingMappingContext());
        Map<Status, List<Auction>> auctionsPerStatus = auctions.stream().collect(Collectors.groupingBy(Auction::getStatus));
        report.setBidsPerUser(userBidCounts);
        report.setAuctionsPerAuctioneer(userAuctionCountDTOS);
        report.setAuctionsPerStatus(auctionsPerStatus);
        return report;
    }

    private static void writeObjectsToCsv(Report reportBundle, OutputStreamWriter outputStreamWriter) throws IOException {
        try (CSVWriter writer = new CSVWriter(outputStreamWriter)) {
            // Write CSV header
            String[] header = {"************ Number of auctions per Status ***************************"};
            writer.writeNext(header);

            // Write CSV data
            Map<Status, List<Auction>> auctionsPerStatus = reportBundle.getAuctionsPerStatus();

            writer.writeNext(new String[]{"Status", "Auction ID", "Item Name", "Reserved Price", "startTime", "endTime"});
            auctionsPerStatus.forEach((status, auctions) -> {
                auctions.forEach(auction -> {
                    writer.writeNext(new String[]{status.toString(),
                            String.valueOf(auction.getId()),
                            auction.getTitle(),
                            String.valueOf(auction.getReservedPrice()),
                            String.valueOf(auction.getStartTime()),
                            String.valueOf(auction.getEndTime())});
                });
            });

            writer.writeNext(new String[]{"************ Top Participants based on number of bids  ***************************"});
            List<UserFrequency> bidsPerUser = reportBundle.getBidsPerUser();
            writer.writeNext(new String[]{"Participant", "Number of bids"});
            bidsPerUser.stream()
                    .sorted(Comparator.comparingLong(UserFrequency::getNumberOfAuctions).reversed())
                    .limit(10)
                    .forEach(userFrequency -> writer.writeNext(new String[]{userFrequency.getEmail(), String.valueOf(userFrequency.getNumberOfAuctions())}));

            writer.writeNext(new String[]{"************ Top Auctioneers  based on number of Auctions  ***************************"});
            List<UserFrequency> auctionsPerAuctioneer = reportBundle.getAuctionsPerAuctioneer();
            writer.writeNext(new String[]{"Auctioneers", "Number of auctions"});
            auctionsPerAuctioneer.stream()
                    .sorted(Comparator.comparingLong(UserFrequency::getNumberOfAuctions).reversed())
                    .limit(10)
                    .forEach(userFrequency -> writer.writeNext(new String[]{userFrequency.getEmail(), String.valueOf(userFrequency.getNumberOfAuctions())}));
        }
    }
}
