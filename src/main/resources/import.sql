INSERT INTO worker (worker_sec,identification, name) VALUES (nextval('worker_sec_seq'),'65465465', 'Carolina');
INSERT INTO worker (worker_sec,identification, name) VALUES (nextval('worker_sec_seq'),'156165156', 'Felipe');
INSERT INTO worker (worker_sec,identification, name) VALUES (nextval('worker_sec_seq'),'165511132', 'Camila');

INSERT INTO support (support_sec,description, complexity, worker) VALUES (nextval('support_sec_seq'),'Soporte técnico 1', 10,  1);
INSERT INTO support (support_sec,description, complexity, worker) VALUES (nextval('support_sec_seq'),'Soporte técnico 2', 10,  1);
INSERT INTO support (support_sec,description, complexity, worker) VALUES (nextval('support_sec_seq'),'Soporte técnico 3', 10,  1);

INSERT INTO support (support_sec,description, complexity, worker) VALUES (nextval('support_sec_seq'),'Soporte técnico 1', 30,  2);

INSERT INTO support (support_sec,description, complexity, worker) VALUES (nextval('support_sec_seq'),'Soporte técnico 1', 10,  3);
INSERT INTO support (support_sec,description, complexity, worker) VALUES (nextval('support_sec_seq'),'Soporte técnico 2', 10,  3);