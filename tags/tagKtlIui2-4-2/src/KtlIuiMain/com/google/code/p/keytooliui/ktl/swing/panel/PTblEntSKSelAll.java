/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 /**
     
 **/
package com.google.code.p.keytooliui.ktl.swing.panel;

import java.awt.event.MouseListener;
import com.google.code.p.keytooliui.ktl.lang.bool.BOEntrySK;
import com.google.code.p.keytooliui.ktl.swing.table.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.lang.bool.*;
import com.google.code.p.keytooliui.shared.swing.table.*;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

final public class PTblEntSKSelAll extends PTblEntSKAbs 
{
    // --------------
    // static private
    
    // not working coz not taking into account length of columns' titles
    static private int _s_getWidth()
    {
        int intVal = 0;
        
        for (int i=0; i<TMEntSKSelAll.f_s_intsColW.length; i++)
            intVal += TMEntSKSelAll.f_s_intsColW[i];
        
        // dummy value
        intVal += 100;
        
        return intVal;
    }
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    //final static private int _f_s_intDeltaW = 0; // using default width assigned in superclass
    
    // ------
    // PUBLIC

    public boolean load(
        Boolean[] boosIsCandidate, // for selection
        String[] strsAlias,
        Date[] dtesLastModified
        )
    {
        String strMethod = "load(...)";
        
        
        if (boosIsCandidate==null || strsAlias==null || dtesLastModified==null)
        {
            MySystem.s_printOutError(this, strMethod, "nil arg");
            return false;
        }
        
        int intNbRow = strsAlias.length;
        
        if (intNbRow < 0)
        {
            MySystem.s_printOutError(this, strMethod, "wrong value, intNbRow=" + intNbRow);
            return false;
        }
        

        if (intNbRow!=dtesLastModified.length || intNbRow!=boosIsCandidate.length)
        {
            MySystem.s_printOutError(this, strMethod, "wrong args length");
            return false;
        }
        
        // -----
        
        Object[][] objssData = new Object[intNbRow][];
        
        for (int i=0; i<intNbRow; i++)
        {   
            BOCheckedCandidateAbs bocIsCandidate = new BOCheckedCandidateRed(boosIsCandidate[i].booleanValue());
            
            // trick, tempo, in order to show icon of type "key""
            BOEntrySK bocIsKeyEntry = new BOEntrySK();
            
            if (! bocIsCandidate.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            if (! bocIsKeyEntry.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
            
            
            Object[] objsLine = {
                bocIsCandidate,
                strsAlias[i],
                bocIsKeyEntry,
                dtesLastModified[i]
                };
                
             objssData[i] = objsLine;   
        }
        
        
        if (! _load(objssData))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        return true;        
    }
    
    public PTblEntSKSelAll(
        ListSelectionListener lsnListenerParent, MouseListener mouListenerParent
        )
    {
        super(PTblEntSKSelAll._s_getWidth(), lsnListenerParent, mouListenerParent); 
        super._mtm_ = new TMEntSKSelAll(); 
    }
    
    public PTblEntSKSelAll()
    {
        this((ListSelectionListener) null, (MouseListener) null);
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._mtm_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._mtm_");
            return false;
        }
        
        TableEntryAbs tbl = new TableEntSKSelAll((TMEntSKSelAll) super._mtm_);
        this.setBackground(tbl.getBackground());
        
        if (! _initColumnSize(tbl))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        super._addListeners_(tbl);
        
        if (super._speList_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._speList_");
            return false;
        }
       
        super._speList_.setViewportView(tbl);
        
        // ending
        return true;
    }
    
    
    
    // -------
    // PRIVATE
    
    
    
    private boolean _initColumnSize(JTable tbl)
    {
        String strMethod = "_initColumnSize(tbl)";
        
        if (tbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tbl");
            return false;
        }

        for (int i=0; i<TMEntSKSelAll.f_s_intsColW.length; i++)
        {
            TableColumnModel tcm = tbl.getColumnModel();
            
            if (tcm == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil tcm");
                return false;
            }
            
            TableColumn tcn = tcm.getColumn(i);
            
            if (tcn == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil tcn");
                return false;
            }
            
            TableCellRenderer tcrHeader = tcn.getHeaderRenderer();
     
            // ADDITION IN ORDER TO HANDLE CHANGES BETWEEN JDK1.2.2 & JDK1.3final       
            if (tcrHeader == null)
            {
                JTableHeader thr = tbl.getTableHeader();
                
                if (thr == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil thr");
                    return false;
                }
                
                tcrHeader = thr.getDefaultRenderer();
                
                if (tcrHeader == null)
                {
                    MySystem.s_printOutError(this, strMethod, "nil tcrHeader");
                    return false;
                }
            }
          
            Object objHeaderValue = tcn.getHeaderValue();
            
            if (objHeaderValue == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil objHeaderValue");
                return false;
            }
            
            Component cmp = tcrHeader.getTableCellRendererComponent(
                tbl, objHeaderValue, 
                false, false, 0, i);
            
            // ----
            
            if (cmp == null)
            {
                MySystem.s_printOutError(this, strMethod, "nil cmp");
                return false;
            }
            
            int intHeaderWidth = cmp.getPreferredSize().width;
            
            tcn.setPreferredWidth(Math.max(intHeaderWidth, TMEntSKSelAll.f_s_intsColW[i]));
            tcn.setMinWidth(Math.max(intHeaderWidth, TMEntSKSelAll.f_s_intsColW[i]));
        }

        // ending
        return true;
    }
    
    
    
    
    private boolean _load(Object[][] objssData)
    {
        String strMethod = "_load(objssData)";
        
        if (objssData == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil objssData");
            return false;
        }
        
        super._mtm_ = new TMEntSKSelAll(objssData);
            
        if (! super._mtm_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
           
        TableEntryAbs tbl = new TableEntSKSelAll((TMEntSKSelAll) super._mtm_);
        this.setBackground(tbl.getBackground());
            
        if (! _initColumnSize(tbl))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        super._addListeners_(tbl);
        
        if (! _setRendererStyled(tbl))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ---------
        
        if (super._speList_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._speList_");
            return false;
        }
            
        super._speList_.setViewportView(tbl);
    
        // ending
        return true;
    }
    
    private boolean _setRendererStyled(JTable tbl)
    {
        String strMethod = "_setRendererStyled(tbl)";
        
        if (tbl == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil tbl");
            return false;
        }
        
        // 
        TableColumnModel tcm = tbl.getColumnModel();
        
        // --
        TCRAbs tcr = null;
        TableColumn tcnCur = null;
        
        // --
        tcr = new TCRCheckedCandidate();
        tcnCur = tcm.getColumn(TMEntSKSelAll.f_s_intColumnIdIsCandidate);
        tcnCur.setCellRenderer(tcr);
        
        // --
        tcr = new TCRCheckedDot();
        tcnCur = tcm.getColumn(TMEntSKSelAll.f_s_intColumnIdIsSKEntry);
        tcnCur.setCellRenderer(tcr);
        
    
        // ending
        return true;
    }
}
