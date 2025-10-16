-- Insert Customer Data (CustomerID, Password, Balance)
INSERT INTO customer (customer_id, password, balance) VALUES ('Cust001', 'Cust001pass', 9000.00);
INSERT INTO customer (customer_id, password, balance) VALUES ('Cust002', 'Cust002pass', 15000.00);
INSERT INTO customer (customer_id, password, balance) VALUES ('Cust003', 'Cust003pass', 500.00);
INSERT INTO customer (customer_id, password, balance) VALUES ('Cust004', 'Cust004pass', 20000.00);
INSERT INTO customer (customer_id, password, balance) VALUES ('Cust005', 'Cust005pass', 850.00);

-- Insert Coupon Data (CouponCode, OfferPercentage)
INSERT INTO coupon (coupon_code, offer_percentage) VALUES ('NY2020-00', 0);
INSERT INTO coupon (coupon_code, offer_percentage) VALUES ('NY2020-05', 5);
INSERT INTO coupon (coupon_code, offer_percentage) VALUES ('NY2020-10', 10);
INSERT INTO coupon (coupon_code, offer_percentage) VALUES ('NY2020-20', 20);
