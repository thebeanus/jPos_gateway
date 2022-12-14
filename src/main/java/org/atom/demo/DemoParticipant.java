package org.atom.demo;


import org.jpos.iso.*;
import org.jpos.transaction.*;
import java.util.Random;
import java.io.Serializable;


public class DemoParticipant extends TxnSupport  {
    /* (non-Javadoc)
     * @see org.jpos.transaction.TxnSupport#doPrepare(long, org.jpos.transaction.Context)
     */
    @Override
    protected int doPrepare(long id, Context ctx) throws Exception {
        ISOMsg message = (ISOMsg) ctx.get(ContextConstants.REQUEST.toString());
        ISOSource source = (ISOSource) ctx.get(ContextConstants.SOURCE.toString());
        assertNotNull(message, "A valid 'REQUEST' is expected in the context");
        assertNotNull(source, "A valid 'SOURCE' is expected in the context");
        assertTrue(message.hasField(4),
                "The message needs to have an amount (ISOMsg:4)");
        message.setResponseMTI();
        Random random = new Random(System.currentTimeMillis());
        message.set(37, Integer.toString(Math.abs(random.nextInt()) % 1000000));
        /* This is the original code: 
        *   message.set(38, Integer.toString(Math.abs(random.nextInt()) % 1000000));
        */
        message.set(38, Integer.toString(1234566));

        /* This is the original logic to check the message value. 
         *  We use this later
         *   if ("000000009999".equals(message.getString(4)) || "0200".equals(message.getMTI()))  
         *       message.set(39, "01");
         *   else
         *       message.set(39, "00");
         */
        
        source.send(message);
        return PREPARED | NO_JOIN | READONLY;
    }

    @Override
    public void commit(long id, Serializable context) {
        // Nothing #TODO here
    }

    @Override
    public void abort(long id, Serializable context) {
         // Nothing #TODO here
    }
}