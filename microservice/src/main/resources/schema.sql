CREATE DATABASE IF NOT EXISTS postgres;
GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres;

CREATE TABLE tb_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50) NOT NULL
);


CREATE TABLE tb_auctions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    reserved_price NUMERIC(19, 2) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    auctioneer_id BIGINT NOT NULL
);

ALTER TABLE tb_auctions
ADD CONSTRAINT fk_auction_auctioneer
FOREIGN KEY (auctioneer_id) REFERENCES tb_users(id);

CREATE TABLE bids (
    id BIGSERIAL PRIMARY KEY,
    bid_amount NUMERIC(19, 2) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    auction_id BIGINT NOT NULL,
    participant_id BIGINT NOT NULL
);

ALTER TABLE bids
ADD CONSTRAINT fk_bid_auction
FOREIGN KEY (auction_id) REFERENCES tb_auctions(id);

ALTER TABLE bids
ADD CONSTRAINT fk_bid_participant
FOREIGN KEY (participant_id) REFERENCES tb_users(id);





