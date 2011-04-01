/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.scrollpane;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.textpane.*;
import com.google.code.p.keytooliui.shared.io.*;


final public class SPEditorDefaultJar extends SPEditorAbstract
{    
    // ------
    // PUBLIC
    
    public boolean doFileOpen(byte[] bytsBuffer)
        throws Exception
    {
        String f_strMethod = "doFileOpen(bytsBuffer)";
        
        if (! ((TPEditorDefaultJar) super._ntt_).doFileOpen(bytsBuffer))
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        setEnabled(true);
        return true;
    }
    
    public boolean writeTo(FileJar fjr, String strPathRelative)
        throws Exception
    {
        return ((TPEditorDefaultJar) super._ntt_).writeTo(fjr, strPathRelative);
    }
    
    public SPEditorDefaultJar()
    {
        super();
	    super._ntt_ = new TPEditorDefaultJar();
    }

    // -------
    // PRIVATE
}