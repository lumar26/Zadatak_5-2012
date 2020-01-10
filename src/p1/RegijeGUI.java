package p1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegijeGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panelWest;
	private JTextField txtNaziv;
	private JTextField txtNatalitet;
	private JTextField txtMortalitet;
	private JTextField txtMigracioniSaldo;
	private JLabel lblNaziv;
	private JLabel lblNatalitet;
	private JLabel lblMortalitet;
	private JLabel lblMigracioniSaldo;
	private JPanel panelSouth;
	private JButton btnDodaj;
	private JButton btnSacuvaj;
	private JButton btnObrisi;
	private JTextArea textArea;

	private ArrayList<Regija> regioni = new ArrayList<Regija>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegijeGUI frame = new RegijeGUI();
					frame.setVisible(true);
					frame.setTitle("Demografski podaci po regionima");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegijeGUI() {
		setTitle("Demografski podaci po regionima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelWest(), BorderLayout.WEST);
		contentPane.add(getPanelSouth(), BorderLayout.SOUTH);
		contentPane.add(getTextArea(), BorderLayout.CENTER);
	}

	private JPanel getPanelWest() {
		if (panelWest == null) {
			panelWest = new JPanel();
			panelWest.setLayout(null);
			panelWest.setPreferredSize(new Dimension(120, 80));
			panelWest.add(getTxtNaziv());
			panelWest.add(getTxtNatalitet());
			panelWest.add(getTxtMortalitet());
			panelWest.add(getTxtMigracioniSaldo());
			panelWest.add(getLblNaziv());
			panelWest.add(getLblNatalitet());
			panelWest.add(getLblMortalitet());
			panelWest.add(getLblMigracioniSaldo());
		}
		return panelWest;
	}

	private JTextField getTxtNaziv() {
		if (txtNaziv == null) {
			txtNaziv = new JTextField();
			txtNaziv.setBounds(0, 54, 116, 22);
			txtNaziv.setColumns(10);
		}
		return txtNaziv;
	}

	private JTextField getTxtNatalitet() {
		if (txtNatalitet == null) {
			txtNatalitet = new JTextField();
			txtNatalitet.setBounds(0, 119, 116, 22);
			txtNatalitet.setColumns(10);
		}
		return txtNatalitet;
	}

	private JTextField getTxtMortalitet() {
		if (txtMortalitet == null) {
			txtMortalitet = new JTextField();
			txtMortalitet.setBounds(0, 182, 116, 22);
			txtMortalitet.setColumns(10);
		}
		return txtMortalitet;
	}

	private JTextField getTxtMigracioniSaldo() {
		if (txtMigracioniSaldo == null) {
			txtMigracioniSaldo = new JTextField();
			txtMigracioniSaldo.setBounds(0, 247, 116, 22);
			txtMigracioniSaldo.setColumns(10);
		}
		return txtMigracioniSaldo;
	}

	private JLabel getLblNaziv() {
		if (lblNaziv == null) {
			lblNaziv = new JLabel("Naziv");
			lblNaziv.setHorizontalAlignment(SwingConstants.CENTER);
			lblNaziv.setBounds(29, 25, 56, 16);
		}
		return lblNaziv;
	}

	private JLabel getLblNatalitet() {
		if (lblNatalitet == null) {
			lblNatalitet = new JLabel("Natalitet");
			lblNatalitet.setHorizontalAlignment(SwingConstants.CENTER);
			lblNatalitet.setBounds(29, 89, 56, 16);
		}
		return lblNatalitet;
	}

	private JLabel getLblMortalitet() {
		if (lblMortalitet == null) {
			lblMortalitet = new JLabel("Mortalitet");
			lblMortalitet.setHorizontalAlignment(SwingConstants.CENTER);
			lblMortalitet.setBounds(29, 154, 56, 16);
		}
		return lblMortalitet;
	}

	private JLabel getLblMigracioniSaldo() {
		if (lblMigracioniSaldo == null) {
			lblMigracioniSaldo = new JLabel("Migracioni Saldo");
			lblMigracioniSaldo.setHorizontalAlignment(SwingConstants.CENTER);
			lblMigracioniSaldo.setBounds(0, 217, 116, 16);
		}
		return lblMigracioniSaldo;
	}

	private JPanel getPanelSouth() {
		if (panelSouth == null) {
			panelSouth = new JPanel();
			panelSouth.setPreferredSize(new Dimension(30, 120));
			panelSouth.add(getBtnDodaj());
			panelSouth.add(getBtnSacuvaj());
			panelSouth.add(getBtnObrisi());
		}
		return panelSouth;
	}

	private JButton getBtnDodaj() {
		if (btnDodaj == null) {
			btnDodaj = new JButton("Dodaj");
			btnDodaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					unesiRegijuUListu(new Regija(txtNaziv.getText(), Integer.parseInt(txtNatalitet.getText()),
							Integer.parseInt(txtMortalitet.getText())));
				}
			});
		}
		return btnDodaj;
	}

	private JButton getBtnSacuvaj() {
		if (btnSacuvaj == null) {
			btnSacuvaj = new JButton("Sacuvaj");
			btnSacuvaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Regija regija : regioni) {
						if (!(regijaJeUListi(regija))
								&& (regija.getNatalitet() - regija.getMortalitet() + regija.getMigracioniSaldo()) > 0)
							Regija.sacuvajRegiju(regija, "rastuce_regije.out");
						if (!(regijaJeUListi(regija))
								&& (regija.getNatalitet() - regija.getMortalitet() + regija.getMigracioniSaldo()) < 0)
							Regija.sacuvajRegiju(regija, "umiruce_regije.out");
					}

				}
			});
		}
		return btnSacuvaj;
	}

	private JButton getBtnObrisi() {
		if (btnObrisi == null) {
			btnObrisi = new JButton("Obrisi");
			btnObrisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textArea.setText(null);
					txtMigracioniSaldo.setText(null);
					txtMortalitet.setText(null);
					txtNatalitet.setText(null);
					txtNaziv.setText(null);
				}
			});
		}
		return btnObrisi;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	public boolean regijaJeUListi(Regija reg) {
		for (Regija regija : regioni) {
			if (regija.equals(reg))
				return true;
		}
		return false;
	}

	public void unesiRegijuUListu(Regija regija) {
		int brojac = 1;
		if (!regijaJeUListi(regija)) {
			// da li je moguce unositi nove elemente pomocu foreach petlje ?!
			for (Regija reg : regioni) {
				if(regija.getNatalitet() <= reg.getNatalitet()) break;
				else brojac++;
			}
			regioni.add(brojac, regija);
		}
			
		else
			JOptionPane.showMessageDialog(null, "Regija se vec nalazi u listi", "Obavestenje",
					JOptionPane.INFORMATION_MESSAGE);
	}
}
