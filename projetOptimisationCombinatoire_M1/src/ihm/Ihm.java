package ihm;

import glouton.AlgorithmeGlouton;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import branchAndBound.AlgorithmeBranchAndBound;
import model.Model;
import readers.Lecteur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Ihm {

	private JFrame frmOptimisationCombinatoire;
	private JList<String> listeEntreprises;
	private JList<String> listeBases;
	private JRadioButton rdbtnBranchBound;
	private JRadioButton rdbtnGlouton;
	private ButtonGroup choixAlgorithme;
	private JTextArea textAreaResultats;
	
	private JButton btnLogs;
	private Logs logs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ihm window = new Ihm();
					window.frmOptimisationCombinatoire.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ihm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOptimisationCombinatoire = new JFrame();
		frmOptimisationCombinatoire.setTitle("Optimisation combinatoire");
		frmOptimisationCombinatoire.setBounds(100, 100, 659, 465);
		frmOptimisationCombinatoire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOptimisationCombinatoire.getContentPane().setLayout(new BorderLayout(0, 0));
		
		logs = new Logs();
		
		JPanel panelResultats = new JPanel();
		panelResultats.setBorder(new TitledBorder(null, "R\u00E9sultats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmOptimisationCombinatoire.getContentPane().add(panelResultats, BorderLayout.CENTER);
		panelResultats.setLayout(new BorderLayout(0, 0));
		
		textAreaResultats = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAreaResultats, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelResultats.add(scrollPane, BorderLayout.CENTER);
		textAreaResultats.setEditable(false);
		textAreaResultats.setEditable(false);
		
		JPanel panelOptions = new JPanel();
		frmOptimisationCombinatoire.getContentPane().add(panelOptions, BorderLayout.WEST);
		panelOptions.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAlgorithmes = new JPanel();
		panelAlgorithmes.setBorder(new TitledBorder(null, "Algorithmes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOptions.add(panelAlgorithmes, BorderLayout.SOUTH);
		panelAlgorithmes.setLayout(new BoxLayout(panelAlgorithmes, BoxLayout.X_AXIS));
		
		Box verticalBoxPanelOptions = Box.createVerticalBox();
		panelAlgorithmes.add(verticalBoxPanelOptions);
		
		Box horizontalBoxTitre = Box.createHorizontalBox();
		verticalBoxPanelOptions.add(horizontalBoxTitre);
		
		Box horizontalBoxOptions = Box.createHorizontalBox();
		verticalBoxPanelOptions.add(horizontalBoxOptions);
		
		Box verticalBoxChoix = Box.createVerticalBox();
		horizontalBoxOptions.add(verticalBoxChoix);
		
		choixAlgorithme = new ButtonGroup();
		
		rdbtnBranchBound = new JRadioButton("Branch & Bound");
		verticalBoxChoix.add(rdbtnBranchBound);
		choixAlgorithme.add(rdbtnBranchBound);
		
		rdbtnGlouton = new JRadioButton("Glouton");
		verticalBoxChoix.add(rdbtnGlouton);
		choixAlgorithme.add(rdbtnGlouton);
		
		rdbtnBranchBound.setSelected(true);
		
		JPanel panelEntreprisesBases = new JPanel();
		panelOptions.add(panelEntreprisesBases, BorderLayout.NORTH);
		panelEntreprisesBases.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEntreprises = new JPanel();
		panelEntreprises.setBorder(new TitledBorder(null, "Entreprises", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntreprisesBases.add(panelEntreprises, BorderLayout.NORTH);
		panelEntreprises.setLayout(new BorderLayout(0, 0));
		
		listeEntreprises = new JList<String>(remplirListeEntreprises());
		listeEntreprises.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelEntreprises.add(listeEntreprises, BorderLayout.CENTER);
		listeEntreprises.setSelectedIndex(0);
		
		JPanel panelBases = new JPanel();
		panelBases.setBorder(new TitledBorder(null, "Bases", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntreprisesBases.add(panelBases, BorderLayout.SOUTH);
		panelBases.setLayout(new BorderLayout(0, 0));
		
		listeBases = new JList<String>(remplirListeBases());
		listeBases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelBases.add(listeBases, BorderLayout.CENTER);
		listeBases.setSelectedIndex(0);
		
		JPanel panelLancer = new JPanel();
		frmOptimisationCombinatoire.getContentPane().add(panelLancer, BorderLayout.SOUTH);
		panelLancer.setLayout(new BoxLayout(panelLancer, BoxLayout.X_AXIS));
		
		Box horizontalBoxLancement = Box.createHorizontalBox();
		panelLancer.add(horizontalBoxLancement);
		
		Component horizontalGlueLancement = Box.createHorizontalGlue();
		horizontalBoxLancement.add(horizontalGlueLancement);
		
		JButton btnLancer = new JButton("Lancer");
		btnLancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Nettoyage de l'écran des résultat 
				// Masquage du bouton logs
				// Masquage de l'écran des logs
				textAreaResultats.setText("");
				btnLogs.setVisible(false);
				logs.setVisible(false);
				
				// Initialisation du compteur temps;
				long debut = System.currentTimeMillis();
				
				// Choix des fichiers
				String cheminListeBases = "./bin/docs/listesBases/" + listeBases.getSelectedValue();
				String cheminListeEntreprises = "./bin/docs/listesEntreprises/" + listeEntreprises.getSelectedValue();
				
				// Lecture des fichiers
				Lecteur lecteur = new Lecteur(cheminListeBases, cheminListeEntreprises);
				Model model = new Model();
				lecteur.lireBases();
				model.setBases(lecteur.getBases());
				model.setEntreprises(lecteur.lireEntreprises());
				model.setEntreprisesBases(lecteur.getEntreprisesBases());
				
				// Temps intermédiaire = chargement des fichiers
				long inter = System.currentTimeMillis();
				textAreaResultats.append(" CHARGEMENT DES FICHIERS : " + (inter - debut) + " ms\n");
				
				if (rdbtnBranchBound.isSelected()) {
					AlgorithmeBranchAndBound algoBB = new AlgorithmeBranchAndBound(model);
					algoBB.executer();
					// Temps final = temps d'éxecution de l'algorithme
					long fin = System.currentTimeMillis();
					textAreaResultats.append(" EXECUTION DE L'ALGORITHME DE BRANCH & BOUND : " + (fin - inter) + " ms\n\n");
					textAreaResultats.append(algoBB.afficherResultat());
					logs.setLogs(algoBB.getLogs());
					btnLogs.setVisible(true);
				}
				
				if (rdbtnGlouton.isSelected()) {
					AlgorithmeGlouton algoGlouton = new AlgorithmeGlouton(model);
					// Temps final = temps d'éxecution de l'algorithme
					long fin = System.currentTimeMillis();
					textAreaResultats.append(" EXECUTION DE L'ALGORITHME GLOUTON : " + (fin - inter) + " ms\n\n");
					textAreaResultats.append(algoGlouton.afficherResultat());
					logs.setLogs(algoGlouton.getLogs());
					btnLogs.setVisible(true);
				}			
			}
		});
		
		btnLogs = new JButton("Logs");
		btnLogs.setVisible(false);
		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logs.setVisible(true);
			}
		});
		horizontalBoxLancement.add(btnLogs);
		btnLancer.setHorizontalAlignment(SwingConstants.RIGHT);
		horizontalBoxLancement.add(btnLancer);
	}
	
	private DefaultListModel<String> remplirListeBases() { 
		File[] fichiers = null; 
		File repertoire = new File("bin/docs/listesBases"); 
		fichiers = repertoire.listFiles();
		ArrayList<String> listeTemporaire = new ArrayList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (File file : fichiers) {
			listeTemporaire.add(file.getName());
		}
		Collections.sort(listeTemporaire);
		for (String nomFichier : listeTemporaire) {
			listModel.addElement(nomFichier);
		}
		return listModel;
	}
	
	private DefaultListModel<String> remplirListeEntreprises() { 
		File[] fichiers = null; 
		File repertoire = new File("bin/docs/listesEntreprises"); 
		fichiers = repertoire.listFiles();
		ArrayList<String> listeTemporaire = new ArrayList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (File file : fichiers) {
			listeTemporaire.add(file.getName());
		}
		Collections.sort(listeTemporaire);
		for (String nomFichier : listeTemporaire) {
			listModel.addElement(nomFichier);
		}
		return listModel;
	}

}
