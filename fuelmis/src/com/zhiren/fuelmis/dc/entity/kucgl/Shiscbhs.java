package com.zhiren.fuelmis.dc.entity.kucgl;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class Shiscbhs {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final int k = 10;
    private static final int l = 11;
    private static final int m = 12;
    private static final int n = 13;
    private static final int o = 14;
    private static final int p = 15;
    private static final int q = 16;
    private static final int r = 17;
    private static final int s = 18;
    private static final int t = 19;
    private Object[][] cell = new Object[5][15];
    private ChurkBean oldChurk;
    private ChurkBean newChurk;

    public Shiscbhs() {
        cell[0] = new Object[]{"日期", "记账分类", "记账说明", "数量", "金额", "单位成本", "暂估数量", "暂估金额", "暂估单价", "结余数量", "结余金额", "结余单位成本", "实时煤场数量", "实时煤场金额", "实时煤场单位成本"};
        cell[1][b] = "期初";
        cell[2][b] = "入库";
        cell[3][b] = "入库";
        cell[3][b] = "出库";
        cell[1][c] = "结算入库";
        cell[2][c] = "杂费结算入库";
        cell[3][c] = "生产消耗";
    }


    public ChurkBean getOldChurk() {
        return oldChurk;
    }

    public void setOldChurk(ChurkBean oldChurk) {
        this.oldChurk = oldChurk;
    }

    public ChurkBean getNewChurk() {
        return newChurk;
    }

    public void setNewChurk(ChurkBean newChurk) {
        this.newChurk = newChurk;
    }

    private Object[] jisQickc() {
        //计算期初
        cell[1][i] = (double)cell[1][g] == 0.0 ? (double) cell[1][h] : (double) cell[1][h] / (double) cell[1][g];//=IF(G1=0,H1,ROUND(H1/G1,2))
        cell[1][j] = cell[1][d];//=J1=D1
        cell[1][k] = cell[1][e];//
        cell[1][l] = (double)cell[1][j] == 0.0 ? cell[1][k] : (double) cell[1][k] / (double) cell[1][j];//
        cell[1][m] = (double) cell[1][g] + (double) cell[1][j];//
        cell[1][n] = (double) cell[1][h] + (double) cell[1][h];//
        cell[1][o] = (double)cell[1][m] == 0.0 ? (double) cell[1][n] : (double) cell[1][n] / (double) cell[1][m];//
        return cell[1];
    }

    private Object[] jisJiesrk() {
        cell[2][f] = (double)cell[2][d] == 0.0 ? cell[1][f] : (double) cell[2][e] / (double) cell[2][d];//
        cell[2][g] = (double) cell[1][g] - (double) cell[2][d];//
        cell[2][h] = (double) cell[1][h] - (double) cell[2][e];//
        cell[2][i] = (double)cell[2][g] == 0.0 ? (double) cell[2][h] : (double) cell[2][h] / (double) cell[2][g];//
        cell[2][j] = (double) cell[1][j] + (double) cell[2][d];//
        cell[2][k] = (double) cell[1][k] + (double) cell[2][e];//
        cell[2][l] = (double)cell[2][j] == 0.0 ? cell[2][k] : (double) cell[2][k] / (double) cell[2][j];//
        cell[2][m] = (double) cell[2][g] + (double) cell[2][j];//
        cell[2][n] = (double) cell[2][h] + (double) cell[2][h];//
        cell[2][o] = (double)cell[2][m] == 0.0 ? (double) cell[2][n] : (double) cell[2][n] / (double) cell[2][m];//
        return cell[2];
    }

    private Object[] jisZafJiesrk() {
        //计算杂费结算入库
        cell[3][f] = (double)cell[3][d] == 0.0 ? cell[2][f] : (double) cell[3][e] / (double) cell[3][d];//
        cell[3][g] = (double) cell[2][g] - (double) cell[3][d];//
        cell[3][h] = (double) cell[2][h] - (double) cell[3][e];//
        cell[3][i] = (double)cell[3][g] == 0.0 ? (double) cell[3][h] : (double) cell[3][h] / (double) cell[3][g];//
        cell[3][j] = (double) cell[2][j] + (double) cell[3][d];//
        cell[3][k] = (double) cell[2][k] + (double) cell[2][e];//
        cell[3][l] = (double)cell[3][j] == 0.0 ? cell[3][k] : (double) cell[3][k] / (double) cell[2][j];//
        cell[3][m] = (double) cell[3][g] + (double) cell[3][j];//
        cell[3][n] = (double) cell[3][h] + (double) cell[3][h];//
        cell[3][o] = (double)cell[3][m] == 0.0 ? (double) cell[3][n] : (double) cell[3][n] / (double) cell[3][m];//
        return cell[3];
    }

    private Object[] jisShengcxh() {
        //计算生产消耗
        //金额
        cell[4][e] = (double) cell[3][j]==0.0?0:(double) cell[4][d] * (double) cell[3][k] / (double) cell[3][j];//D4*K2/J2
        cell[4][f] = (double)cell[4][d] == 0.0 ? cell[3][f] : (double) cell[4][e] / (double) cell[4][d];//f4=IF(D4=0,F3,ROUND(E4/D4,2))
        cell[4][g] = (double) cell[4][d] < 0 ? cell[3][g] : (double) cell[3][g] - (double) cell[4][d];//=IF(D4<0,G3,G3-D4)
        cell[4][h] = (double) cell[4][e] < 0 ? cell[3][h] : (double) cell[3][h] - (double) cell[4][e];//=IF(E4<0,H3,H3-E4)
        cell[4][i] = (double)cell[4][g] == 0.0 ? (double) cell[4][h] : (double) cell[4][h] / (double) cell[4][g];//=IF(G4=0,H4,ROUND(H4/G4,2))
        cell[4][j] = (double) cell[3][j] + (double) cell[4][d];//=J3+D4
        cell[4][k] = (double) cell[3][k] + (double) cell[4][e];//=K3+E4
        cell[4][l] = (double)cell[4][j] == 0.0 ? cell[4][k] : (double) cell[4][k] / (double) cell[4][j];//=IF(J4=0,K4,ROUND(K4/J4,2))
        cell[4][m] = (double) cell[4][g] + (double) cell[4][j];//=G4+J4
        cell[4][n] = (double) cell[4][h] + (double) cell[4][h];//=H4+K4
        cell[4][o] = (double)cell[4][m] == 0.0? (double) cell[4][n] : (double) cell[4][n] / (double) cell[4][m];//=IF(M4=0,N4,ROUND(N4/M4,2))
        return cell[4];
    }

    private void calculate() {
        //计算期初
        this.jisQickc();
        this.jisJiesrk();
        this.jisZafJiesrk();
        this.jisShengcxh();
    }

    public void updateQicChurkbean(ChurkBean nChurk) {
        Object[] Out = new Object[]{nChurk.getRiq(), nChurk.getJizfl(), nChurk.getJizsm(), nChurk.getShul(), nChurk.getJine(), nChurk.getDanwcb(),
                nChurk.getZangsl(), nChurk.getZangje(), nChurk.getZangdj(), nChurk.getJieysl(), nChurk.getJine(), nChurk.getJieycb(), nChurk.getShismcsl(), nChurk.getShismcje(), nChurk.getDanwcb()};
        cell[1] = Out;
        Out = this.jisQickc();
        nChurk.setRiq(Out[0].toString());
        nChurk.setJizfl(Out[1].toString());
        nChurk.setJizsm(Out[2].toString());
        nChurk.setShul((double) Out[3]);
        nChurk.setJine((double) Out[4]);
        nChurk.setDanwcb((double) Out[5]);
        nChurk.setZangsl((double) Out[6]);
        nChurk.setZangje((double) Out[7]);
        nChurk.setZangdj((double) Out[8]);
        nChurk.setJieysl((double) Out[9]);
        nChurk.setJine((double) Out[10]);
        nChurk.setJieycb((double) Out[11]);
        nChurk.setShismcsl((double) Out[12]);
        nChurk.setShismcje((double) Out[13]);
        nChurk.setDanwcb((double) Out[14]);
    }

    /**
     * 更新出入库的其他量
     *
     * @param oChurk 输入量
     * @param nChurk 输出量
     */
    public void updateNewChurkbean(ChurkBean oChurk, ChurkBean nChurk) {
        Object[] In = new Object[]{oChurk.getRiq(), oChurk.getJizfl(), oChurk.getJizsm(), oChurk.getShul(), oChurk.getJine(), oChurk.getDanwcb(),
                oChurk.getZangsl(), oChurk.getZangje(), oChurk.getZangdj(), oChurk.getJieysl(), oChurk.getJieyje(), oChurk.getJieycb(), oChurk.getShismcsl(), oChurk.getShismcje(), oChurk.getDanwcb()};
        Object[] Out = new Object[]{nChurk.getRiq(), nChurk.getJizfl(), nChurk.getJizsm(), nChurk.getShul(), nChurk.getJine(), nChurk.getDanwcb(),
                nChurk.getZangsl(), nChurk.getZangje(), nChurk.getZangdj(), nChurk.getJieysl(), nChurk.getJieyje(), nChurk.getJieycb(), nChurk.getShismcsl(), nChurk.getShismcje(), nChurk.getDanwcb()};
        switch (nChurk.getYewlx()) {
            case 0://计算期初
                cell[1] = Out;
                Out = this.jisQickc();
                break;
            case 1://计算结算入库
                cell[1] = In;
                cell[2] = Out;
                Out = this.jisJiesrk();
                break;
            case 2://计算杂费入库
                cell[2] = In;
                cell[3] = Out;
                Out = this.jisZafJiesrk();
                break;
            case 6://计算生产消耗
                cell[3] = In;
                cell[4] = Out;
                Out = this.jisShengcxh();
                break;
        }
        nChurk.setRiq(Out[0].toString());
        nChurk.setJizfl(Out[1].toString());
        nChurk.setJizsm(Out[2].toString());
        nChurk.setShul((double) Out[3]);
        nChurk.setJine((double) Out[4]);
        nChurk.setDanwcb((double) Out[5]);
        nChurk.setZangsl((double) Out[6]);
        nChurk.setZangje((double) Out[7]);
        nChurk.setZangdj((double) Out[8]);
        nChurk.setJieysl((double) Out[9]);
        nChurk.setJine((double) Out[10]);
        nChurk.setJieycb((double) Out[11]);
        nChurk.setShismcsl((double) Out[12]);
        nChurk.setShismcje((double) Out[13]);
        nChurk.setDanwcb((double) Out[14]);
    }
    public void iterate(ChurkBean baseBean, List<ChurkBean> newBeans) {
    	this.updateNewChurkbean(baseBean, newBeans.get(0));
    	for (int index = 0; index < newBeans.size()-1; index++) {
			this.updateNewChurkbean(newBeans.get(index)	, newBeans.get(index+1));
			
		}
    }
}
