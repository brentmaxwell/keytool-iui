/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.beans.swing.panel;

/*
    displays contents of page in a secondary window
    
    "Rtp" means "RTP-RTSP"
    
    "VideoOnly" means waiting for a unique rtp-stream of type video
*/

import javax.media.rtp.*;
import javax.swing.*;


final public class PPageMediaRtpVideoOnly extends PPageMediaRtpVideoAbs
{        
    // ------
    // PUBLIC
    
    
    public PPageMediaRtpVideoOnly(
        String strIPTransmitter,
        int intPortTransmitter,
        int intTTL,
        javax.media.ControllerListener ctrListenerToolbar,
        String strTitleAppli,
        JFrame frmParent,
        boolean blnReadyStart,
        java.awt.Component cmpParent,
        ReceiveStreamListener rsmListenerParent,
        ReceiveStreamListener rsmListenerToolbar
        )
    {
        super(
            strIPTransmitter, 
            intPortTransmitter, 
            com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.f_s_strCntTypeMediaVideoOnly,
            intTTL,
            ctrListenerToolbar,
            strTitleAppli,
            frmParent,
            blnReadyStart,
            cmpParent,
            false, // blnMerge2Streams
            rsmListenerParent,
            rsmListenerToolbar
            );
    }
}