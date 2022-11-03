/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myreversi;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author CE160324_Nguyen Duong Phu Trong
 */
public class Card extends JLabel {

    int col, row, value;

    private MouseListener mouseClicked;
    private MyReversi parent;

    public Card(MyReversi parent, int row, int col, int value) {
        this.col = col;
        this.row = row;
        this.value = value;
        this.parent = parent;

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.mouseClicked = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardClicked();
            }
        };

        this.addMouseListener(mouseClicked);

        updateFace();
    }

    private void cardClicked() {

        int count = parent.countAllDirection(row, col);

        if (count > 0) {
            turnOn(parent.getLuotDi());
            parent.luotDiTiepTheo();
            this.removeMouseListener(mouseClicked);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            parent.updatePoint();
        }

    }

    public void updateFace() {
        this.setIcon(getFace());
    }

    private ImageIcon getFace() {
        return new ImageIcon(getClass().getResource("/img/" + value + ".png"));
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateFace();
    }

    public void turnOn(int luotDi) {
        this.value = value * MyReversi.turnOnValue + luotDi;
        updateFace();
    }

    public void reTurn() {

        int backGround = this.value / MyReversi.turnOnValue;
        int v = this.value % MyReversi.turnOnValue;
        v = 1 - v;

        this.value = backGround * MyReversi.turnOnValue + v;
        updateFace();
    }

   
}
