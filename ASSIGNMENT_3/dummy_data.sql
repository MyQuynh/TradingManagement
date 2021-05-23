-- DROP DATABASE IF EXISTS trading;
SET session_replication_role = 'replica';

/*Create dummy data for category*/
insert into category values (1,'Book');
insert into category values (2,'Makeup');
insert into category values (3,'Clothes');
insert into category values (4,'Technology');
insert into category values (5,'Food');

/*Creat dummy data for customer*/
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (1, 'Gardy', 'Garter', 'ggarter0@weebly.com', 'Gardy Garter', 'ggarter0@newsvine.com', '965-957-6451', '411-540-2789');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (2, 'Wes', 'Szimoni', 'wszimoni1@nps.gov', 'Wes Szimoni', 'wszimoni1@myspace.com', '598-419-6453', '704-450-0848');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (3, 'Kathye', 'Iacobacci', 'kiacobacci2@washingtonpost.com', 'Kathye Iacobacci', 'kiacobacci2@cdbaby.com', '934-811-3010', '762-370-0260');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (4, 'Shannen', 'Shawcroft', 'sshawcroft3@dailymail.co.uk', 'Shannen Shawcroft', 'sshawcroft3@wufoo.com', '739-746-7000', '633-164-2968');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (5, 'Allix', 'Pendlebery', 'apendlebery4@cloudflare.com', 'Allix Pendlebery', 'apendlebery4@gravatar.com', '844-720-1249', '826-535-8530');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (6, 'Gwenette', 'MacSporran', 'gmacsporran5@cmu.edu', 'Gwenette MacSporran', 'gmacsporran5@theatlantic.com', '846-966-8140', '181-359-9812');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (7, 'Beatrisa', 'Crutchley', 'bcrutchley6@a8.net', 'Beatrisa Crutchley', 'bcrutchley6@skyrock.com', '273-262-4812', '225-104-9749');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (8, 'Che', 'Chatain', 'cchatain7@howstuffworks.com', 'Che Chatain', 'cchatain7@usgs.gov', '908-453-5472', '482-353-2477');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (9, 'Mathilde', 'Dolle', 'mdolle8@wiley.com', 'Mathilde Dolle', 'mdolle8@cdc.gov', '993-282-0838', '199-568-2725');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (10, 'Simone', 'Szepe', 'sszepe9@instagram.com', 'Simone Szepe', 'sszepe9@independent.co.uk', '662-450-2560', '429-470-5378');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (11, 'Gardy', 'Garter', 'ggarter0@weebly.com', 'Gardy Garter', 'ggarter0@newsvine.com', '965-957-6451', '411-540-2789');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (12, 'Wes', 'Szimoni', 'wszimoni1@nps.gov', 'Wes Szimoni', 'wszimoni1@myspace.com', '598-419-6453', '704-450-0848');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (13, 'Kathye', 'Iacobacci', 'kiacobacci2@washingtonpost.com', 'Kathye Iacobacci', 'kiacobacci2@cdbaby.com', '934-811-3010', '762-370-0260');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (14, 'Shannen', 'Shawcroft', 'sshawcroft3@dailymail.co.uk', 'Shannen Shawcroft', 'sshawcroft3@wufoo.com', '739-746-7000', '633-164-2968');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (15, 'Allix', 'Pendlebery', 'apendlebery4@cloudflare.com', 'Allix Pendlebery', 'apendlebery4@gravatar.com', '844-720-1249', '826-535-8530');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (16, 'Gwenette', 'MacSporran', 'gmacsporran5@cmu.edu', 'Gwenette MacSporran', 'gmacsporran5@theatlantic.com', '846-966-8140', '181-359-9812');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (17, 'Beatrisa', 'Crutchley', 'bcrutchley6@a8.net', 'Beatrisa Crutchley', 'bcrutchley6@skyrock.com', '273-262-4812', '225-104-9749');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (18, 'Che', 'Chatain', 'cchatain7@howstuffworks.com', 'Che Chatain', 'cchatain7@usgs.gov', '908-453-5472', '482-353-2477');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (19, 'Mathilde', 'Dolle', 'mdolle8@wiley.com', 'Mathilde Dolle', 'mdolle8@cdc.gov', '993-282-0838', '199-568-2725');
insert into customer (id, firstname, lastname, address, contactperson, email, fax, phone) values (20, 'Simone', 'Szepe', 'sszepe9@instagram.com', 'Simone Szepe', 'sszepe9@independent.co.uk', '662-450-2560', '429-470-5378');

/*Create dummy data delivery_detail */
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (1, 32, 7, 5);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (2, 75, 2, 7);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (3, 39, 1, 7);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (4, 11, 8, 4);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (5, 50, 5, 2);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (6, 71, 3, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (7, 89, 8, 5);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (8, 18, 6, 10);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (9, 30, 5, 4);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (10, 59, 3, 5);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (11, 44, 3, 7);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (12, 95, 6, 7);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (13, 43, 6, 5);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (14, 91, 5, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (15, 48, 9, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (16, 63, 7, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (17, 9, 8, 1);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (18, 2, 1, 9);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (19, 99, 1, 10);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (20, 6, 4, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (21, 78, 5, 9);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (22, 95, 6, 1);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (23, 74, 3, 1);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (24, 13, 2, 1);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (25, 82, 2, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (26, 10, 8, 6);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (27, 84, 5, 10);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (28, 53, 4, 3);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (29, 81, 8, 10);
insert into delivery_detail (id, quantity, deliveryNote_id, product_id) values (30, 3, 10, 5);

-- Create dummy data for delivery note
insert into delivery_note (id, date, staff_id) values (1, '2020-08-26', 2);
insert into delivery_note (id, date, staff_id) values (2, '2020-07-06', 5);
insert into delivery_note (id, date, staff_id) values (3, '2021-02-02', 4);
insert into delivery_note (id, date, staff_id) values (4, '2020-10-24', 4);
insert into delivery_note (id, date, staff_id) values (5, '2021-01-24', 9);
insert into delivery_note (id, date, staff_id) values (6, '2020-06-09', 3);
insert into delivery_note (id, date, staff_id) values (7, '2021-01-27', 5);
insert into delivery_note (id, date, staff_id) values (8, '2020-09-29', 6);
insert into delivery_note (id, date, staff_id) values (9, '2020-09-25', 7);
insert into delivery_note (id, date, staff_id) values (10, '2020-06-13', 9);

-- Create dummy data for order detail
insert into order_detail (id, price, quantity, order_id, product_id) values (1, 84, 8, 5, 8);
insert into order_detail (id, price, quantity, order_id, product_id) values (2, 100, 4, 9, 3);
insert into order_detail (id, price, quantity, order_id, product_id) values (3, 45, 6, 3, 8);
insert into order_detail (id, price, quantity, order_id, product_id) values (4, 48, 9, 3, 3);
insert into order_detail (id, price, quantity, order_id, product_id) values (5, 54, 3, 1, 8);
insert into order_detail (id, price, quantity, order_id, product_id) values (6, 12, 7, 2, 6);
insert into order_detail (id, price, quantity, order_id, product_id) values (7, 55, 8, 8, 5);
insert into order_detail (id, price, quantity, order_id, product_id) values (8, 22, 5, 1, 6);
insert into order_detail (id, price, quantity, order_id, product_id) values (9, 9, 1, 9, 6);
insert into order_detail (id, price, quantity, order_id, product_id) values (10, 79, 6, 7, 7);
insert into order_detail (id, price, quantity, order_id, product_id) values (11, 14, 8, 4, 4);
insert into order_detail (id, price, quantity, order_id, product_id) values (12, 17, 4, 7, 6);
insert into order_detail (id, price, quantity, order_id, product_id) values (13, 41, 7, 8, 10);
insert into order_detail (id, price, quantity, order_id, product_id) values (14, 9, 1, 2, 5);
insert into order_detail (id, price, quantity, order_id, product_id) values (15, 97, 6, 2, 10);
insert into order_detail (id, price, quantity, order_id, product_id) values (16, 4, 4, 10, 1);
insert into order_detail (id, price, quantity, order_id, product_id) values (17, 10, 7, 6, 9);
insert into order_detail (id, price, quantity, order_id, product_id) values (18, 30, 8, 3, 9);
insert into order_detail (id, price, quantity, order_id, product_id) values (19, 9, 8, 10, 5);
insert into order_detail (id, price, quantity, order_id, product_id) values (20, 22, 8, 9, 10);
insert into order_detail (id, price, quantity, order_id, product_id) values (21, 39, 8, 5, 1);
insert into order_detail (id, price, quantity, order_id, product_id) values (22, 14, 1, 2, 3);
insert into order_detail (id, price, quantity, order_id, product_id) values (23, 63, 7, 3, 7);
insert into order_detail (id, price, quantity, order_id, product_id) values (24, 87, 2, 1, 3);
insert into order_detail (id, price, quantity, order_id, product_id) values (25, 16, 7, 1, 2);
insert into order_detail (id, price, quantity, order_id, product_id) values (26, 12, 1, 2, 9);
insert into order_detail (id, price, quantity, order_id, product_id) values (27, 88, 2, 5, 2);
insert into order_detail (id, price, quantity, order_id, product_id) values (28, 92, 10, 9, 9);
insert into order_detail (id, price, quantity, order_id, product_id) values (29, 36, 1, 7, 8);
insert into order_detail (id, price, quantity, order_id, product_id) values (30, 27, 4, 1, 7);

-- create dummy data for order
insert into order_service (id, date, provider_id, staff_id) values (1, '2021-03-31', 5, 4);
insert into order_service (id, date, provider_id, staff_id) values (2, '2020-09-08', 7, 5);
insert into order_service (id, date, provider_id, staff_id) values (3, '2020-09-03', 10, 2);
insert into order_service (id, date, provider_id, staff_id) values (4, '2020-08-29', 6, 6);
insert into order_service (id, date, provider_id, staff_id) values (5, '2020-09-23', 6, 7);
insert into order_service (id, date, provider_id, staff_id) values (6, '2020-10-22', 7, 3);
insert into order_service (id, date, provider_id, staff_id) values (7, '2020-06-20', 2, 7);
insert into order_service (id, date, provider_id, staff_id) values (8, '2021-04-05', 5, 6);
insert into order_service (id, date, provider_id, staff_id) values (9, '2020-06-28', 6, 10);
insert into order_service (id, date, provider_id, staff_id) values (10, '2020-09-01', 10, 3);

-- create dummy data for product
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (1, 'Extra-Virt Plus DHA', 'Thoughtsphere', 'Maxillary fracture, left side, 7thD', 'Explorer', 'Chocolate - Liqueur Cups With Foil', 45, 25, 2);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (2, 'Phoma glomerata', 'Brainsphere', 'Nondisp fx of r radial styloid pro, 7thE', 'Xterra', 'Wine - Ruffino Chianti', 9, 88, 3);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (3, 'Prestige Snail Lip Treatment', 'Babbleset', 'Azoospermia due to other extratesticular causes', 'Corvette', 'Leeks - Large', 58, 79, 1);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (4, 'FUSARIUM VASINFECTUM OXYSPORUM', 'Demivee', 'Puncture wound with foreign body of larynx, init encntr', '1500', 'Wine - Magnotta - Belpaese', 79, 42, 2);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (5, 'Sun Mark Allergy', 'Browsebug', 'Advrs effect of slctv seroton/norepineph reup inhibtr, subs', 'Model S', 'Jolt Cola - Electric Blue', 16, 36, 3);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (6, 'Homeopathic HCG Formula', 'Rhybox', 'Toxic effect of soaps, undetermined', 'DeVille', 'Wine - Savigny - Les - Beaune', 95, 20, 1);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (7, 'Indomethacin', 'Twitternation', 'Unsp injury of flexor musc/fasc/tend thmb at forarm lv, subs', '500SEC', 'Pastry - French Mini Assorted', 27, 34, 5);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (8, 'good sense cool heat', 'Npath', 'Unsp physl fx upr end rad, unsp arm, 7thD', 'DTS', 'Wine - Jackson Triggs Okonagan', 67, 14, 2);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (9, 'Fluoxetine Hydrochloride', 'Jayo', 'Burn of second degree of right toe(s) (nail)', 'Econoline E350', 'Dried Figs', 60, 61, 5);
insert into product (id, brand, company, description, model, name, price, quantity, category_id) values (10, 'Direct Safety Aspirin', 'Dabtype', 'Other disorders of phosphorus metabolism', 'Econoline E250', 'Salt - Seasoned', 87, 7, 5);

-- create dummy data for provider
insert into provider (id, address, contact_person, email, fax, name, phone) values (1, '0717 Mallory Center', 'Boote De Paoli', 'mmulhill0@t.co', '111-138-8422', 'Murry Mulhill', '427-346-5435');
insert into provider (id, address, contact_person, email, fax, name, phone) values (2, '8 Talmadge Place', 'Llywellyn Gullberg', 'nflitcroft1@mlb.com', '195-344-4418', 'Nicky Flitcroft', '980-468-1368');
insert into provider (id, address, contact_person, email, fax, name, phone) values (3, '2296 Tomscot Hill', 'Rebeca Renard', 'bivermee2@bing.com', '453-652-7110', 'Beverly Ivermee', '668-504-3172');
insert into provider (id, address, contact_person, email, fax, name, phone) values (4, '3783 Arapahoe Way', 'Maren Crawcour', 'njosskowitz3@independent.co.uk', '328-751-0022', 'Nat Josskowitz', '822-788-6967');
insert into provider (id, address, contact_person, email, fax, name, phone) values (5, '31363 Red Cloud Circle', 'Leisha Tume', 'ddominik4@rakuten.co.jp', '504-577-3504', 'Darline Dominik', '106-740-9759');
insert into provider (id, address, contact_person, email, fax, name, phone) values (6, '16 Anthes Hill', 'Minette Hartmann', 'eselwyn5@guardian.co.uk', '729-228-4911', 'Em Selwyn', '791-448-7073');
insert into provider (id, address, contact_person, email, fax, name, phone) values (7, '28 Forest Dale Park', 'Marty Girod', 'mwoodyear6@linkedin.com', '773-523-8241', 'Maridel Woodyear', '571-673-3362');
insert into provider (id, address, contact_person, email, fax, name, phone) values (8, '67 Coolidge Pass', 'Ellswerth Critcher', 'cperren7@reddit.com', '264-829-5273', 'Collete Perren', '481-209-8651');
insert into provider (id, address, contact_person, email, fax, name, phone) values (9, '67003 Derek Place', 'Whitby Bordes', 'cchittock8@comcast.net', '192-478-6262', 'Colby Chittock', '906-990-3215');
insert into provider (id, address, contact_person, email, fax, name, phone) values (10, '44621 Waxwing Plaza', 'Nadia Cranson', 'mmccrow9@nydailynews.com', '563-936-7034', 'Mair McCrow', '225-488-7804');

-- create dummy data for receiving detail
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (1, 19, 2, 7);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (2, 40, 4, 10);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (3, 57, 6, 10);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (4, 76, 6, 5);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (5, 53, 5, 2);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (6, 31, 7, 2);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (7, 94, 1, 2);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (8, 87, 5, 5);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (9, 66, 6, 9);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (10, 17, 3, 6);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (11, 43, 10, 10);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (12, 43, 5, 9);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (13, 36, 6, 4);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (14, 61, 6, 1);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (15, 65, 5, 1);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (16, 56, 3, 8);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (17, 54, 10, 6);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (18, 56, 8, 1);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (19, 27, 7, 10);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (20, 66, 2, 5);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (21, 77, 10, 7);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (22, 23, 7, 3);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (23, 63, 1, 6);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (24, 69, 4, 5);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (25, 78, 8, 9);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (26, 15, 1, 3);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (27, 47, 5, 3);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (28, 87, 2, 1);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (29, 82, 4, 2);
insert into receiving_detail (id, quantity, product_id, receivingnote_id) values (30, 41, 4, 1);

-- create dummy data for receiving note
insert into receiving_note (id, date, staff_id) values (1, '2020-08-17', 1);
insert into receiving_note (id, date, staff_id) values (2, '2020-07-12', 10);
insert into receiving_note (id, date, staff_id) values (3, '2021-04-13', 5);
insert into receiving_note (id, date, staff_id) values (4, '2021-01-02', 9);
insert into receiving_note (id, date, staff_id) values (5, '2020-11-14', 3);
insert into receiving_note (id, date, staff_id) values (6, '2020-07-01', 3);
insert into receiving_note (id, date, staff_id) values (7, '2020-05-29', 4);
insert into receiving_note (id, date, staff_id) values (8, '2020-07-23', 2);
insert into receiving_note (id, date, staff_id) values (9, '2020-06-09', 8);
insert into receiving_note (id, date, staff_id) values (10, '2021-04-18', 4);

-- create dummy data for sale detail
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (1, 19, 4, 6, 2);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (2, 31, 2, 3, 2);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (3, 15, 10, 1, 4);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (4, 68, 9, 7, 10);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (5, 78, 4, 6, 1);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (6, 21, 1, 7, 9);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (7, 11, 5, 2, 6);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (8, 64, 4, 4, 3);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (9, 94, 10, 8, 7);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (10, 21, 6, 10, 7);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (11, 59, 7, 6, 5);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (12, 46, 9, 10, 6);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (13, 45, 4, 5, 3);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (14, 50, 4, 3, 5);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (15, 78, 10, 9, 1);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (16, 31, 6, 10, 2);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (17, 27, 5, 5, 5);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (18, 37, 9, 5, 6);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (19, 25, 10, 7, 2);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (20, 34, 3, 6, 3);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (21, 3, 2, 7, 5);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (22, 17, 2, 3, 9);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (23, 21, 6, 3, 7);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (24, 80, 4, 9, 5);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (25, 53, 6, 1, 8);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (26, 74, 9, 1, 6);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (27, 99, 3, 9, 7);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (28, 69, 9, 2, 1);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (29, 69, 5, 7, 8);
insert into sale_detail (id, price, quantity, product_id, salesinvoice) values (30, 76, 2, 1, 10);

-- create dummy data for sale invoice
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (1, '2021-03-16', 66, 8, 4);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (2, '2020-10-06', 65, 7, 7);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (3, '2020-10-13', 40, 10, 8);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (4, '2020-09-10', 100, 3, 4);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (5, '2021-01-19', 87, 8, 7);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (6, '2020-10-18', 41, 6, 7);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (7, '2020-08-26', 36, 1, 2);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (8, '2020-10-03', 64, 6, 2);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (9, '2020-06-14', 92, 9, 3);
insert into sales_invoice (id, date, total_value, customer_id, staff_id) values (10, '2020-12-27', 45, 4, 4);

-- create dummy data for staff
insert into staff (id, address, email, firstname, lastname, phone) values (1, '91 Sunnyside Center', 'dbrient0@weebly.com', 'Dulcinea', 'Brient', '534-694-0663');
insert into staff (id, address, email, firstname, lastname, phone) values (2, '5 Nevada Junction', 'pfarfalameev1@tumblr.com', 'Pen', 'Farfalameev', '274-759-0043');
insert into staff (id, address, email, firstname, lastname, phone) values (3, '2 Acker Lane', 'lspurdon2@aol.com', 'Linnet', 'Spurdon', '116-194-0757');
insert into staff (id, address, email, firstname, lastname, phone) values (4, '2140 Morningstar Center', 'kricker3@wsj.com', 'Kilian', 'Ricker', '175-822-0671');
insert into staff (id, address, email, firstname, lastname, phone) values (5, '1808 Warner Park', 'mseamen4@1688.com', 'Mady', 'Seamen', '195-242-8354');
insert into staff (id, address, email, firstname, lastname, phone) values (6, '7 Calypso Point', 'ojosefson5@ow.ly', 'Osmond', 'Josefson', '187-333-9505');
insert into staff (id, address, email, firstname, lastname, phone) values (7, '85133 Lakewood Gardens Street', 'kkeller6@histats.com', 'Kenneth', 'Keller', '234-417-5269');
insert into staff (id, address, email, firstname, lastname, phone) values (8, '16 Mayfield Park', 'earboine7@nymag.com', 'Ema', 'Arboine', '579-765-7150');
insert into staff (id, address, email, firstname, lastname, phone) values (9, '5673 Maywood Avenue', 'jhubbins8@google.ca', 'Josey', 'Hubbins', '129-105-4826');
insert into staff (id, address, email, firstname, lastname, phone) values (10, '8 Schlimgen Alley', 'blaugier9@goo.ne.jp', 'Bogart', 'Laugier', '462-176-3140');

SET session_replication_role = 'origin';


-- select the inventory
select product.id ,product.name, count(receiving_detail.id) as "Received", count(sale_detail.id) as "Delivery", count(delivery_detail.id) as "BALANCE" from product, receiving_detail, sale_detail, delivery_detail
where product.id = receiving_detail.product_id and sale_detail.product_id = product.id and receiving_detail.receivingnote_id = product.id
group by product.id

select product.id ,product.name, COALESCE(receiving_detail.receive, 0) receive, COALESCE(delivery_detail.delivery, 0) delivery, COALESCE(sale_detail.balance, 0) balance
from product
left join (
    select product_id, count(receiving_detail) as receive
    from receiving_detail, receiving_note
    where receiving_detail.receivingnote_id = receiving_note.id
    and receiving_note.date >= '2020-02-01'
    and receiving_note.date <  '2021-03-01'
    group by receiving_detail.product_id
) receiving_detail on product.id = receiving_detail.product_id

left join (
    select product_id, count(delivery_detail) as delivery
    from delivery_detail, delivery_note
    where delivery_detail.deliverynote_id = delivery_note.id
      and delivery_note.date >= '2020-02-01'
      and delivery_note.date <  '2021-03-01'
    group by delivery_detail.product_id
) delivery_detail on delivery_detail.product_id = product.id

left join (
    select sale_detail.product_id, sum(sale_detail.price * sale_detail.quantity) as balance
    from sale_detail, sales_invoice
    where sale_detail.salesinvoice = sales_invoice.id
      and sales_invoice.date >= '2020-02-01'
      and sales_invoice.date <  '2021-03-01'
    group by sale_detail.product_id
) sale_detail on sale_detail.product_id = product.id
