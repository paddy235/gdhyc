select * from rl_ht_caigddb; --��������
select * from rl_ht_caigddb_sub; --��������Ϣ��������ID
select * from rl_ht_price_scheme; -- �Ƽ����������ָ�������ã����磺��������֣�����ҳ�棬�ж����е�ID
select * from  RL_HT_KAOHZBB  ; -- �������ݣ��Ƽ����ݣ����мƼ۷�����ID
select * from rl_ht_price_item; --��ͬ����ָ������
select * from rl_ht_price_component;---- ����ָ��ļƼ۹�ʽ


------------------------------------------------

alter table RL_HT_KAOHZBB RENAME COLUMN CAIGDDB_JIJFS to scheme_id

