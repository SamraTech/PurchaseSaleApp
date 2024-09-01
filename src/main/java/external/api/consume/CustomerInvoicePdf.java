package external.api.consume;

import java.awt.Color;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import external.api.consume.model.SaleOrder;
import external.api.consume.model.SaleOrderDtl;

public class CustomerInvoicePdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hi");
		response.addHeader("Content:Disposition", "attachment:filename=customerinvoice.pdf");
		SaleOrder order = (SaleOrder) model.get("saleOrderById");
		List<SaleOrderDtl> dtls = (List<SaleOrderDtl>) model.get("saleOrderDtl");
		Font f = new Font();
		f.setColor(Color.blue);
		f.isUnderlined();

		tableData(f, order, dtls, document);

	}

	private static void tableData(Font f, SaleOrder order, List<SaleOrderDtl> dtls, Document document) {

		Paragraph p = new Paragraph("CustomerInvoice", f);

		PdfPTable header = new PdfPTable(4);
		header.addCell("SaleOrderCode");
		header.addCell(order.getOrderCode());

		header.addCell("CustomerCode");
		header.addCell(order.getUser().getUsr_Code());
		header.addCell("ShipmentCode");
		header.addCell(order.getShipment().getShpmnt_Code());
		header.addCell("FinalCost");
		Double total = 0.0;

		PdfPTable table = new PdfPTable(8);

		for (SaleOrderDtl dtl : dtls) {

			total += dtl.getQty() * dtl.getPart().getPartCost();
			table.addCell("Item Name");
			table.addCell(dtl.getPart().getPartCode());
			table.addCell("Item Cost");
			table.addCell(String.valueOf(dtl.getPart().getPartCost()));
			table.addCell("Item Qty");
			table.addCell(String.valueOf(dtl.getQty()));
			table.addCell("Item Value");
			table.addCell(String.valueOf(dtl.getPart().getPartCost() * dtl.getQty()));
		}

		header.addCell(String.valueOf(total));

		// total = total + dtl.getQty() * dtl.getPart().getPartCost();

		p.add(header);
		p.add(table);
		LocalDate now = LocalDate.now();

		document.add(p);

	}

}
