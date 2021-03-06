package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    "Kpr" for "keypair"
**/

import com.google.code.p.keytooliui.ktl.swing.dialog.*;

import com.google.code.p.keytooliui.shared.lang.*;


// ----
import java.security.KeyStore;

// --
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public final class KTLKprOpenSigDetOutPkcs12 extends KTLKprOpenSigDetOutAbs
{
    // ------
    // PUBLIC

    public KTLKprOpenSigDetOutPkcs12(
        Frame frmOwner, 
      
        
        // input
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        String strPathAbsFileOpenData,
        
        String strPathAbsFileSaveSig, // signature to save
        String strPathAbsFileSaveCrt, // certificate to save, optional
        String strFormatFileSig, // should be binary
        String strFormatFileSaveCrt // ie DER, PKCS7, PEM
        )
    {
        super(
            frmOwner, 
      
        
            // input
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
            strPathAbsFileOpenData,
        
            strPathAbsFileSaveSig, // signature to save
            strPathAbsFileSaveCrt, // certificate to save, optional
                
            strFormatFileSig, // should be binary
            strFormatFileSaveCrt,
            KTLAbs.f_s_strProviderKstPkcs12
            );
            
    }
    
    // ---------
    // protected
    
    protected KeyStore _getKeystoreOpen_(File fleOpen)
    {
        return UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, 
         
            fleOpen,
            super._chrsPasswdKst_);
    }
    
    protected boolean _doJobSelectKpr_(
        File fleSaveSig,
        File fleSaveCrt,
        File fleOpenData,
        KeyStore kstOpen,
            
        // NEW
        // below: about PKTC (Private Key & Trusted Certificate)
        String[] strsAliasPKTC, 
        Boolean[] boosIsTCEntryPKTC, 
        Boolean[] boosValidDatePKTC, 
        Boolean[] boosSelfSignedCertPKTC, 
        Boolean[] boosTrustedCertPKTC, 
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC, 
        String[] strsAlgoSigCertPKTC, 
        Date[] dtesLastModifiedPKTC,
        // below: about SK (Secret Key)
        String[] strsAliasSK,
        Date[] dtesLastModifiedSK
        )
    {
        String strMethod = "_doJobSelectKpr_(...)";

        DTblsKstSelPKOpenNoPass dlg = new DTblsKstSelPKOpenNoPass(
            super._frmOwner_, 
         
            kstOpen,
            super._strPathAbsKst_,
            "Sign file with private key and save as other detached signature file"
            );
        
        if (! dlg.init())
            MySystem.s_printOutExit(this, strMethod, "failed");
        
        // 
        if (! dlg.load(
            // below: about PKTC (Private Key & Trusted Certificate)
            strsAliasPKTC, 
            boosIsTCEntryPKTC, 
            boosValidDatePKTC, 
            boosSelfSignedCertPKTC, 
            boosTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, 
            dtesLastModifiedPKTC,
            // below: about SK (Secret Key)
            strsAliasSK,
            dtesLastModifiedSK))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }   
        
        dlg.setVisible(true);
        
        // ----
        
        String strAliasKpr = dlg.getAlias();
        
        if (strAliasKpr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil strAliasKpr, aborted by user");
            return false;
        }
        
        super._setEnabledCursorWait_(true);

        if (! super._doJob_(kstOpen, strAliasKpr, new char[0], fleSaveSig, fleSaveCrt, fleOpenData))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);
        return true;
    }
}
