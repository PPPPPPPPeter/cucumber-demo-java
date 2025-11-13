package ui;

import game.GobangGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GobangUI extends JFrame {
    private GobangGame game;
    private BoardPanel boardPanel;
    private JLabel statusLabel;
    private JButton resetButton;

    public GobangUI() {
        game = new GobangGame();
        initUI();
    }

    private void initUI() {
        setTitle("Gobang Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Board panel
        boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();
        statusLabel = new JLabel("Current Player: Black");
        resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> resetGame());

        controlPanel.add(statusLabel);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void resetGame() {
        game.startNewGame();
        statusLabel.setText("Current Player: Black");
        boardPanel.repaint();
    }

    private class BoardPanel extends JPanel {
        private static final int CELL_SIZE = 40;
        private static final int PIECE_SIZE = 30;
        private static final int MARGIN = 40;

        public BoardPanel() {
            setPreferredSize(new Dimension(
                    game.getBoardSize() * CELL_SIZE + 2 * MARGIN,
                    game.getBoardSize() * CELL_SIZE + 2 * MARGIN
            ));
            setBackground(new Color(220, 179, 92));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (game.isGameOver()) {
                        return;
                    }

                    int col = (e.getX() - MARGIN + CELL_SIZE / 2) / CELL_SIZE;
                    int row = (e.getY() - MARGIN + CELL_SIZE / 2) / CELL_SIZE;

                    if (game.placePiece(row, col)) {
                        repaint();

                        if (game.isGameOver()) {
                            statusLabel.setText("Winner: " + game.getWinner() + "!");
                            JOptionPane.showMessageDialog(
                                    GobangUI.this,
                                    game.getWinner() + " wins!",
                                    "Game Over",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            statusLabel.setText("Current Player: " + game.getCurrentPlayer());
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw grid
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < game.getBoardSize(); i++) {
                // Vertical lines
                g2d.drawLine(
                        MARGIN + i * CELL_SIZE,
                        MARGIN,
                        MARGIN + i * CELL_SIZE,
                        MARGIN + (game.getBoardSize() - 1) * CELL_SIZE
                );
                // Horizontal lines
                g2d.drawLine(
                        MARGIN,
                        MARGIN + i * CELL_SIZE,
                        MARGIN + (game.getBoardSize() - 1) * CELL_SIZE,
                        MARGIN + i * CELL_SIZE
                );
            }

            // Draw pieces
            for (int row = 0; row < game.getBoardSize(); row++) {
                for (int col = 0; col < game.getBoardSize(); col++) {
                    int piece = game.getPiece(row, col);
                    if (piece != 0) {
                        int x = MARGIN + col * CELL_SIZE - PIECE_SIZE / 2;
                        int y = MARGIN + row * CELL_SIZE - PIECE_SIZE / 2;

                        if (piece == 1) {
                            g2d.setColor(Color.BLACK);
                        } else {
                            g2d.setColor(Color.WHITE);
                        }

                        g2d.fillOval(x, y, PIECE_SIZE, PIECE_SIZE);
                        g2d.setColor(Color.BLACK);
                        g2d.drawOval(x, y, PIECE_SIZE, PIECE_SIZE);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GobangUI ui = new GobangUI();
            ui.setVisible(true);
        });
    }
}