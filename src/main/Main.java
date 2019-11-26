package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Main {
	
	static File[] selected;
	
	public static void main(String[] args) {
		JFrame f = new JFrame("PDF Merge Utility");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 500);
		f.setVisible(true);
				
		JButton select = new JButton("Select Files");
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selected = chooseFiles();
				JButton merge = new JButton("Merge Selected");
				merge.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Arrays.asList(selected).forEach(x -> {
							System.out.println(x.getName());
						});
					}
				});
				f.getContentPane().add(BorderLayout.EAST, merge);
				f.revalidate();
			}
		});


		
		f.getContentPane().add(BorderLayout.WEST, select);
//		f.getContentPane().add(BorderLayout.EAST, merge);
	}
	
	public static File[] chooseFiles() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select PDFs to Merge");
		jfc.setMultiSelectionEnabled(true);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter f = new FileNameExtensionFilter("pdf", "pdf");
		jfc.addChoosableFileFilter(f);
		
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFiles();
		} else {
			return null;
		}
	}
}
