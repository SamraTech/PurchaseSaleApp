package external.api.consume;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import external.api.consume.model.PurchaseOrder;
import external.api.consume.model.PurchaseOrderDtl;








public   class VendorInvoicePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		response.addHeader("Content-Disposition","attachment;filename=VendorInvoice.pdf");
		List<PurchaseOrderDtl> dtl=(List<PurchaseOrderDtl>)model.get("purchaseDetails");
		PurchaseOrder order=(PurchaseOrder)model.get("purchaseOrder");
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
		 Paragraph p = new Paragraph("Vendor Invoice",font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         PdfPTable header=new PdfPTable(4);
	         header.addCell("OrderCode");
	         header.addCell(order.getOrderCode());
	         header.addCell("ShipmentCode");
	         header.addCell(order.getShipment().getShpmnt_Code());
	         header.addCell("VendorCode");
	         header.addCell(order.getUser().getUsr_Code());
	         header.addCell("Final Cost");
	         double cost=0.0;
	         for(PurchaseOrderDtl dtls:dtl) {
	        	cost+= dtls.getQty()*dtls.getPart().getPartCost();
	         }
	         header.addCell(String.valueOf(cost));
	         p.add(header);
	        document.add(p);
	        document.addTitle("Parts Details");
	        
	        Paragraph prt = new Paragraph("Vendor Invoice",font);
	        prt.setAlignment(Paragraph.ALIGN_CENTER);
	        PdfPTable table=new PdfPTable(4);
	        table.addCell("Code");
	        table.addCell("Cost");
	        table.addCell("Qty");
	        table.addCell("Value");
	        
	        for(PurchaseOrderDtl dtls:dtl) {
	        	 table.addCell(dtls.getPart().getPartCode());
	        	 table.addCell(String.valueOf(dtls.getPart().getPartCost()));
	        	 table.addCell(String.valueOf(dtls.getQty()));
		        table.addCell(String.valueOf(dtls.getPart().getPartCost()*dtls.getQty()));	 
	         }
	        document.add(table);
	        document.add(new Paragraph(new Date().toLocaleString()));
	
	}




	}

	

	

	

	


