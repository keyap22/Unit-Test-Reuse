package hacs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;

/**
 * Title:        HACS
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      msu
 * @author Zhang ji Zhu Wei
 * @version 1.0
 */

public class InstructorAssignmentMenu extends AssignmentMenu
{
  private boolean bSubmit=false;
  private Solution theSolution;
  private Assignment theAssignment;
  JComboBox combSolutionList = new JComboBox();

  JTextField tbAssignmentName = new JTextField();
  JTextField tbDueDate = new JTextField();
  JTextField tbSuggestedSolution = new JTextField();

  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JButton buttonGrade = new JButton();
  JButton buttonReport = new JButton();
  JButton buttonClose = new JButton();

  int x, y, width, height;

  public InstructorAssignmentMenu() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setText("Assignment Name");
    x = 25;
    y = 31;
    width = 118;
    height = 18;
    jLabel1.setBounds(new Rectangle(x, y, width, height));
    this.getContentPane().setLayout(null);
    tbAssignmentName.setText("jTextField1");
    x = 192;
    y = 31;
    width = 341;
    height = 22;
    tbAssignmentName.setBounds(new Rectangle(x, y, width, height));
    jLabel2.setText("Due Date");
    x = 28;
    y = 90;
    width = 113;
    height = 18;
    jLabel2.setBounds(new Rectangle(x, y, width, height));
    tbDueDate.setText("tbDueDate");
    x = 195;
    y = 87;
    width = 337;
    height = 22;
    tbDueDate.setBounds(new Rectangle(x, y, width, height));
    jLabel3.setText("Suggested Solution");
    x = 28;
    y = 151;
    width = 118;
    height = 18;
    jLabel3.setBounds(new Rectangle(x, y, width, height));
    tbSuggestedSolution.setText("jTextField2");
    x = 197;
    y = 149;
    width = 339;
    height = 22;
    tbSuggestedSolution.setBounds(new Rectangle(x, y, width, height));
    buttonGrade.setText("Grade");
    x = 458;
    y = 199;
    width = 79;
    height = 29;
    buttonGrade.setBounds(new Rectangle(x, y, width, height));
    buttonGrade.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonGrade_actionPerformed(e);
      }
    });
    buttonReport.setText("Report");
    x = 365;
    y = 249;
    width = 79;
    height = 29;
    buttonReport.setBounds(new Rectangle(x, y, width, height));
    buttonReport.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonReport_actionPerformed(e);
      }
    });
    buttonClose.setText("Close");
    x = 86;
    y = 253;
    width = 79;
    height = 29;
    buttonClose.setBounds(new Rectangle(x, y, width, height));
    buttonClose.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonClose_actionPerformed(e);
      }
    });
    x = 32;
    y = 204;
    width = 413;
    height = 22;
    combSolutionList.setBounds(new Rectangle(x, y, width, height));
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(tbAssignmentName, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(tbDueDate, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(tbSuggestedSolution, null);
    this.getContentPane().add(buttonClose, null);
    this.getContentPane().add(combSolutionList, null);
    this.getContentPane().add(buttonGrade, null);
    this.getContentPane().add(buttonReport, null);
  }

  public void showMenu(Assignment assignment, Person person) {
    theAssignment = assignment;
    Solution theSolution;
    tbAssignmentName.setText(theAssignment.assName );

    DateFormat theDateFormat = DateFormat.getDateInstance(DateFormat.SHORT );
    tbDueDate.setText(theDateFormat.format(theAssignment.dueDate));
    tbSuggestedSolution.setText(theAssignment.suggestSolution.solutionFileName );
    refreshSolutionList();
    setVisible(true);
  }

  void buttonClose_actionPerformed(ActionEvent e) {
    theAssignment.assName = tbAssignmentName.getText() ;
    DateFormat tempDateFormat = DateFormat.getDateInstance(DateFormat.SHORT );
    try {
      theAssignment.dueDate = tempDateFormat.parse(tbDueDate.getText() );
    } catch (Exception ignored){};
    theAssignment.suggestSolution.solutionFileName = tbSuggestedSolution.getText() ;
    dispose();
  }

  void buttonGrade_actionPerformed(ActionEvent e) {
    Solution theSolution = (Solution)combSolutionList.getSelectedItem() ;
    if (theSolution == null)
       return;
    SolutionGradingDlg dlg = new SolutionGradingDlg();
    dlg.show(theSolution);
    refreshSolutionList();
  }

  void buttonReport_actionPerformed(ActionEvent e) {
    SolutionIterator iter = new SolutionIterator(theAssignment.theSolutionList );
    while(iter.hasNext()) {
      Solution asolution = (Solution)iter.next();
      asolution.setReported(true);
    }
    refreshSolutionList();
  }
  private void refreshSolutionList() {
    combSolutionList.removeAllItems() ;
    SolutionIterator solIter = new SolutionIterator(theAssignment.theSolutionList );
    while(solIter.hasNext() ) {
      theSolution=(Solution)solIter.next();
      combSolutionList.addItem(theSolution);
    }
  }
}
