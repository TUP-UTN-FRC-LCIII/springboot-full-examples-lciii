INSERT INTO dummies (id, version, dummy_info, type, created_date, is_deleted) VALUES (100000, 0, 'Dummy', 'TYPE_A', CURRENT_DATE, false);

INSERT INTO dummy_details (id, dummy_id, dummy_detail)
VALUES (300000, 100000, 'Dummy Detail');

INSERT INTO dummy_items (id, dummy_id, dummy_item_detail, dummy_item_amount, dummy_item_quantity)
VALUES (200000, 100000, 'Dummy Item Detail', 100, 5);
INSERT INTO dummy_items (id, dummy_id, dummy_item_detail, dummy_item_amount, dummy_item_quantity)
VALUES (200001, 100000, 'Dummy Item Detail 2', 200, 10);

