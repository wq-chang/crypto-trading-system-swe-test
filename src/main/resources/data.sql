INSERT INTO supported_cryptos VALUES (DEFAULT, 'BTC', 'Bitcoin');
INSERT INTO supported_cryptos VALUES (DEFAULT, 'ETH', 'Ethereum');
INSERT INTO supported_cryptos VALUES (DEFAULT, 'USDT', 'Tether');

INSERT INTO crypto_pairs VALUES (
    'BTCUSDT',
    SELECT id FROM supported_cryptos WHERE code = 'USDT',
    SELECT id FROM supported_cryptos WHERE code = 'BTC');
INSERT INTO crypto_pairs VALUES (
    'ETHUSDT',
    SELECT id FROM supported_cryptos WHERE code = 'USDT',
    SELECT id FROM supported_cryptos WHERE code = 'ETH');

INSERT INTO users VALUES (1, 'user');

INSERT INTO wallets VALUES (1, 'e7bfbf6d-3ff8-440d-9fff-1a52bfa0aa53', 1);

INSERT INTO wallet_balances VALUES (DEFAULT, 50000, 3, 1);
