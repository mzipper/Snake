package com.zipper.snake;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class SettingsPanel extends JPanel
{
	//Speed components
	private JPanel speedButtonsPanel;
	
	private JRadioButton rBLowSpeed;
	private JRadioButton rBMedSpeed;
	private JRadioButton rBHiSpeed;
	private ButtonGroup group;
	
	
	public SettingsPanel()
	{
		setLayout(new BorderLayout());
		
		buildSpeedRadioButtons();
		add(speedButtonsPanel, BorderLayout.WEST);
		
	} //end of SettingPanel constructor
	
	private void buildSpeedRadioButtons()
	{
		speedButtonsPanel = new JPanel();
		
		//Set some Settings to Panel.
		speedButtonsPanel.setLayout(new GridLayout(3,1));
		speedButtonsPanel.setBorder(BorderFactory.createTitledBorder("Choose Snake Speed"));
	
		//Create RadioButtons.
		rBLowSpeed 	= new JRadioButton("Low Speed");
		rBMedSpeed 	= new JRadioButton("Medium Speed");
		rBHiSpeed 	= new JRadioButton("High Speed (Original)");
		
		//Add RadioButtons to a Group.
		group = new ButtonGroup();
		group.add(rBLowSpeed);
		group.add(rBMedSpeed);
		group.add(rBHiSpeed);
	
		rBHiSpeed.doClick();
	
		//Add RadioButtons to the Panel.
		speedButtonsPanel.add(rBLowSpeed);
		speedButtonsPanel.add(rBMedSpeed);
		speedButtonsPanel.add(rBHiSpeed);
	
		rBLowSpeed.addActionListener(new SpeedRBListener());
		rBMedSpeed.addActionListener(new SpeedRBListener());
		rBHiSpeed.addActionListener(new SpeedRBListener());
	
	} // end of buildSpeedRadioButtons method.
	
	//class enables radio buttons to work.
	private class SpeedRBListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(rBLowSpeed.isSelected())
			{
				ThreadsController.setSpeed(110);
			}
			else if(rBMedSpeed.isSelected())
			{
				ThreadsController.setSpeed(80);
			}
			else if(rBHiSpeed.isSelected())
			{
				ThreadsController.setSpeed(50);
			}
		}
		
	} //end of RadioButtonListener class
} //end of SettingsPanel class
