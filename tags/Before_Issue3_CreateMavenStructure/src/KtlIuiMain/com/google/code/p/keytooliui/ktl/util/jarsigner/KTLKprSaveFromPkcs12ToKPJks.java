package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    KTLKprSaveFromPkcs12Jks: "Kpr" for "KeyPair", create a new entry in existing JKS keystore by importing a KeyPair entry from PKCS12 keystore
    
    
    !!!!!!!!! not done yet: overwritting existing KeyPair entry

**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;

final public class KTLKprSaveFromPkcs12ToKPJks extends KTLKprSaveFromPkcs12ToKPAbs
{
    // ------
    // PUBLIC
    
    /**
        if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        
        . add providers
        
        . open keystoreSource
        . open keystoreKstTarget
        . select aliasSource pointing to valid kprSource
        . get respective keySource & crtsSource
        
        . create new KeyPair
        . create new certificate of type X.509
        . assign new entry to open JKS keystore
        . save JKS keystore
    **/
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        
        // memo: PKCS12 keystore type, provided by "BC" or "SunRsaSign"
        File fleOpenKstSource = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKstSource_);
        
        if (fleOpenKstSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstSource");
            return false;
        }
        
        // ----
        // open PKCS12 keystore
        
        if (super._chrsPasswdKstSource_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKstSource_"); 
        
        KeyStore kstOpenSource = UtilKstPkcs12.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKstSource,
            super._chrsPasswdKstSource_);
        
        if (kstOpenSource == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenSource");
            return false;
        }
            
            
        
        File fleOpenKstTarget = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strPathAbsKst_);
        
        if (fleOpenKstTarget == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKstTarget");
            return false;
        }
        
        // ----
        // open JKS keystore
        
        if (super._chrsPasswdKst_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        
        KeyStore kstOpenTarget = UtilKstJks.s_getKeystoreOpen(
            super._frmOwner_, 
            fleOpenKstTarget,
            super._chrsPasswdKst_);
        
        if (kstOpenTarget == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil kstOpenTarget");
            return false;
        }
        
        super._setEnabledCursorWait_(true);
        
        if (! super._doJob_(kstOpenSource, kstOpenTarget, fleOpenKstTarget))
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        super._setEnabledCursorWait_(false);

        // ending
        return true;
    }
    
    public KTLKprSaveFromPkcs12ToKPJks(
        Frame frmOwner, 
     
        
        // 
        String strPathAbsOpenKst, // existing keystore of type JKS 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx) 
        char[] chrsPasswdKstSource
        )
    {
        super(
            frmOwner, 
       
        
            // 
            strPathAbsOpenKst, // existing keystore of type JKS 
            chrsPasswdOpenKst,
        
            strPathAbsKstSource, // keystore of type PKCS12 (a certificate file suffixed .pfx, p12) 
            chrsPasswdKstSource,
            
            KTLAbs.f_s_strProviderKstJks // provider for keytool of type JKS
            );
    }
    
}