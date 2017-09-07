select * from rl_ht_caigddb; --订单主表
select * from rl_ht_caigddb_sub; --订单行信息，有主表ID
select * from rl_ht_price_scheme; -- 计价组件（考核指标项设置，例如：热量、硫分）设置页面，有订单行的ID
select * from  RL_HT_KAOHZBB  ; -- 考核内容（计价内容），有计价方案的ID
select * from rl_ht_price_item; --合同考核指标设置
select * from rl_ht_price_component;---- 各个指标的计价公式


------------------------------------------------

alter table RL_HT_KAOHZBB RENAME COLUMN CAIGDDB_JIJFS to scheme_id

