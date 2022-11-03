/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myreversi;

import java.awt.GridLayout;

/**
 *
 * @author CE160324_Nguyen Duong Phu Trong
 */
public class MyReversi extends javax.swing.JFrame {

    public static final int numCols = 8;
    public static final int numRows = 8;

    public static final int redChess = 0;
    public static final int blueChess = 1;
    public static final int turnOnValue = 10;

    Card map[][];

    int luotDi; //0: Red, 1:Blue

    public void generateBoard() {

        map = new Card[numRows][numCols];

        pnlBroad.setLayout(new GridLayout(numRows, numCols));
        pnlBroad.removeAll();
        pnlBroad.revalidate();
        pnlBroad.repaint();

        int mau = 1;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {

                mau = ((i + j) % 2 == 0) ? 2 : 1;

                map[i][j] = new Card(this, i, j, mau);
                pnlBroad.add(map[i][j]);
            }
        }

        int value;

        map[3][3].turnOn(redChess);
        map[4][4].turnOn(redChess);
        map[3][4].turnOn(blueChess);
        map[4][3].turnOn(blueChess);

        luotDi = 0;
    }

    public int getLuotDi() {
        return luotDi;
    }

    public void luotDiTiepTheo() {
//        if (luotDi == redChess) {
//            luotDi = blueChess;
//        } else {
//            luotDi = redChess;
//        }
        luotDi = 1 - luotDi;
    }

    public boolean coQuanCo(Card c) {
        return c.getValue() > 9;
    }

    public boolean khacMau(Card c, int luotDi) {
        return (c.getValue() % MyReversi.turnOnValue) != luotDi;
    }

    public boolean cungMau(Card c, int luotDi) {
        return (c.getValue() % MyReversi.turnOnValue) == luotDi;
    }

    public int countUp(int row, int col) {

        int i, count = 0;

        for (i = row - 1; i >= 0; i--) {
            if (coQuanCo(map[i][col])
                    && khacMau(map[i][col], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i >= 0) {
            if (coQuanCo(map[i][col])
                    && cungMau(map[i][col], luotDi)) {

                for (int k = i + 1; k < row; k++) {
                    map[k][col].reTurn();
                }

                return count;
            }
        }

        return 0;
    }

    public int countDown(int row, int col) {

        int i, count = 0;

        for (i = row + 1; i < numRows; i++) {
            if (coQuanCo(map[i][col])
                    && khacMau(map[i][col], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i < numRows) {
            if (coQuanCo(map[i][col])
                    && cungMau(map[i][col], luotDi)) {

                for (int k = row + 1; k < i; k++) {
                    map[k][col].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countLeft(int row, int col) {

        int i, count = 0;

        for (i = col - 1; i >= 0; i--) {
            if (coQuanCo(map[row][i])
                    && khacMau(map[row][i], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i >= 0) {
            if (coQuanCo(map[row][i])
                    && cungMau(map[row][i], luotDi)) {

                for (int k = i + 1; k < col; k++) {
                    map[row][k].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countRight(int row, int col) {

        int i, count = 0;

        for (i = col + 1; i < numCols; i++) {
            if (coQuanCo(map[row][i])
                    && khacMau(map[row][i], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i < numCols) {
            if (coQuanCo(map[row][i])
                    && cungMau(map[row][i], luotDi)) {

                for (int k = col + 1; k < i; k++) {
                    map[row][k].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countLeftUp(int row, int col) {

        int i, j, count = 0;

        for (i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (coQuanCo(map[i][j])
                    && khacMau(map[i][j], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i >= 0 && j >= 0) {
            if (coQuanCo(map[i][j])
                    && cungMau(map[i][j], luotDi)) {

                for (int h = i + 1, k = j + 1; h < row && k < col; h++, k++) {
                    map[h][k].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countRightUp(int row, int col) {

        int i, j, count = 0;

        for (i = row - 1, j = col + 1; i >= 0 && j < numCols; i--, j++) {
            if (coQuanCo(map[i][j])
                    && khacMau(map[i][j], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i >= 0 && j < numCols) {
            if (coQuanCo(map[i][j])
                    && cungMau(map[i][j], luotDi)) {

                for (int h = i + 1, k = j - 1; h < row && k >= col + 1; h++, k--) {
                    map[h][k].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countLeftDown(int row, int col) {

        int i, j, count = 0;

        for (i = row + 1, j = col - 1; i < numRows && j >= 0; i++, j--) {
            if (coQuanCo(map[i][j])
                    && khacMau(map[i][j], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i < numRows && j >= 0) {
            if (coQuanCo(map[i][j])
                    && cungMau(map[i][j], luotDi)) {

                for (int h = i - 1, k = j + 1; h >= row + 1 && k < col; h--, k++) {
                    map[h][k].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countRightDown(int row, int col) {

        int i, j, count = 0;

        for (i = row + 1, j = col + 1; i < numRows && j < numCols; i++, j++) {
            if (coQuanCo(map[i][j])
                    && khacMau(map[i][j], luotDi)) {
                count++;
            } else {
                break;
            }
        }

        if (i < numRows && j < numCols) {
            if (coQuanCo(map[i][j])
                    && cungMau(map[i][j], luotDi)) {

                for (int h = row + 1, k = col + 1; h < i && k < j; h++, k++) {
                    map[h][k].reTurn();
                }
                return count;
            }
        }

        return 0;

    }

    public int countAllDirection(int row, int col) {
        int up = countUp(row, col);
        int down = countDown(row, col);
        int left = countLeft(row, col);
        int right = countRight(row, col);
        int leftUp = countLeftUp(row, col);
        int leftDown = countLeftDown(row, col);
        int rightUp = countRightUp(row, col);
        int rightDown = countRightDown(row, col);

        return up + down + left + right + leftUp + leftDown + rightUp + rightDown;
    }

    public void updatePoint() {
        int countRed = 0, countBlue = 0, v;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (coQuanCo(map[i][j])) {
                    v = map[i][j].getValue() % MyReversi.turnOnValue;
                    if (v == redChess) {
                        countRed++;
                    } else {
                        countBlue++;
                    }
                }
            }
        }
        lblRed.setText(countRed + "");
        lblBlue.setText(countBlue + "");
    }

    /**
     * Creates new form MyReversi
     */
    public MyReversi() {
        initComponents();
        this.setLocationRelativeTo(null);
        generateBoard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGameInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblRed = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBlue = new javax.swing.JLabel();
        pnlBroad = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlGameInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Information"));

        jLabel1.setForeground(new java.awt.Color(255, 0, 102));
        jLabel1.setText("Red:");

        lblRed.setForeground(new java.awt.Color(255, 0, 102));
        lblRed.setText("2");

        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setText("Blue:");

        lblBlue.setForeground(new java.awt.Color(0, 102, 204));
        lblBlue.setText("2");

        javax.swing.GroupLayout pnlGameInfoLayout = new javax.swing.GroupLayout(pnlGameInfo);
        pnlGameInfo.setLayout(pnlGameInfoLayout);
        pnlGameInfoLayout.setHorizontalGroup(
            pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRed)
                .addGap(66, 66, 66)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBlue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGameInfoLayout.setVerticalGroup(
            pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblBlue))
                    .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblRed)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBroad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBroad.setPreferredSize(new java.awt.Dimension(512, 512));
        pnlBroad.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGameInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBroad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlGameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBroad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyReversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyReversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyReversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyReversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyReversi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBlue;
    private javax.swing.JLabel lblRed;
    private javax.swing.JPanel pnlBroad;
    private javax.swing.JPanel pnlGameInfo;
    // End of variables declaration//GEN-END:variables
}
