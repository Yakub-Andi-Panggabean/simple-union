package com.polsri.union.app.servlet.reporting;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.polsri.union.app.dto.OrderDto;
import com.polsri.union.app.service.OrderService;
import com.polsri.union.app.service.PriceService;
import com.polsri.union.app.service.bean.OrderServiceBean;

/**
 * Servlet implementation class ReportingServlet
 */
public class ReportingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WebApplicationContext context;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderService service = (OrderServiceBean) context.getBean("orderServiceBean");
		PriceService priceService = (PriceService) context.getBean("priceServiceBean");
		String companyName = request.getParameter("c");
		String orderId = request.getParameter("i");
		generateReport(request, response, companyName, orderId, service.findOrder(orderId), priceService);
	}

	/**
	 * generate order receipt pdf
	 * 
	 * @param request
	 * @param response
	 * @param companyName
	 * @param orderId
	 * @param data
	 */
	private void generateReport(HttpServletRequest request, HttpServletResponse response, String companyName,
			String orderId, OrderDto data, PriceService priceService) {
		String extension = "pdf";
		Document document = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();

			// create table
			PdfPTable table = new PdfPTable(5);
			PdfPTable tableDetail = new PdfPTable(3);
			table.setWidthPercentage(100);
			PdfPCell title = new PdfPCell(
					new Paragraph("Order Receipt ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16)));
			title.setColspan(5);
			title.setMinimumHeight(30);
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setVerticalAlignment(Element.ALIGN_MIDDLE);
			// title.setBackgroundColor(titleColor);
			title.setBorder(Rectangle.BOTTOM);
			title.setBorderColorBottom(BaseColor.BLACK);

			table.addCell(title);

			PdfPCell cName = new PdfPCell(new Paragraph(companyName, FontFactory.getFont(FontFactory.TIMES_BOLD, 15)));
			cName.setColspan(5);
			cName.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cName.setBorder(Rectangle.NO_BORDER);

			PdfPCell date = new PdfPCell(
					new Paragraph(
							"Transaction Date           : " + new SimpleDateFormat("yyyy - MM - dd, hh : mm : ss")
									.format(data.getTransactionDateTime()),
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
			date.setColspan(5);
			date.setVerticalAlignment(Element.ALIGN_BOTTOM);
			date.setBorder(Rectangle.NO_BORDER);

			PdfPCell transaction = new PdfPCell(
					new Paragraph("Transaction Number      : " + data.getTransactionNumber(),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
			transaction.setColspan(5);
			transaction.setVerticalAlignment(Element.ALIGN_BOTTOM);
			transaction.setBorder(Rectangle.NO_BORDER);

			PdfPCell cashier = new PdfPCell(new Paragraph("Cashier                           : " + data.getUser(),
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
			cashier.setColspan(5);
			cashier.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cashier.setBorder(Rectangle.NO_BORDER);

			PdfPCell emptyRow = new PdfPCell();
			emptyRow.setColspan(5);
			emptyRow.setMinimumHeight(10);
			emptyRow.setBorder(Rectangle.NO_BORDER);

			PdfPCell lineRow = new PdfPCell(new Paragraph(new Chunk(new DottedLineSeparator())));
			lineRow.setColspan(5);
			lineRow.setMinimumHeight(5);
			lineRow.setBorder(Rectangle.NO_BORDER);

			table.addCell(emptyRow);
			table.addCell(cName);
			table.addCell(emptyRow);
			table.addCell(lineRow);
			table.addCell(date);
			table.addCell(transaction);
			table.addCell(cashier);
			table.addCell(lineRow);
			table.addCell(emptyRow);
			table.addCell(emptyRow);
			table.addCell(emptyRow);

			// table detail
			// Price price=null;
			DecimalFormat df = new DecimalFormat("#,###.00");
			for (int i = 0; i < data.getOrderDetails().size(); i++) {
				// find price
				BigDecimal price = priceService.findById(data.getOrderDetails().get(i).getGoodsId()).getPrice();
				// set border to no border

				// column definition
				tableDetail.addCell(new Paragraph(data.getOrderDetails().get(i).getQuantity().toEngineeringString(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
				tableDetail.addCell(new Paragraph(
						data.getOrderDetails().get(i).getGoodsCode() + "-"
								+ data.getOrderDetails().get(i).getGoodsName(),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
				tableDetail
						.addCell(new Paragraph(df.format(data.getOrderDetails().get(i).getQuantity().multiply(price)),
								FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));

				for (int x = 0; x < tableDetail.getRow(i).getCells().length; x++) {
					tableDetail.getRow(i).getCells()[x].setBorder(Rectangle.NO_BORDER);
					tableDetail.getRow(i).getCells()[x].setHorizontalAlignment(Element.ALIGN_CENTER);
					;
				}

			}

			PdfPCell totalRow = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
			totalRow.setHorizontalAlignment(Element.ALIGN_CENTER);
			totalRow.setBorder(Rectangle.NO_BORDER);

			PdfPCell space = new PdfPCell(new Paragraph(""));
			space.setBorder(Rectangle.NO_BORDER);

			PdfPCell totalValue = new PdfPCell(
					new Paragraph(df.format(data.getTotalPayment()), FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
			totalValue.setBorder(Rectangle.NO_BORDER);
			totalValue.setHorizontalAlignment(Element.ALIGN_CENTER);

			tableDetail.addCell(emptyRow);
			tableDetail.addCell(totalRow);
			tableDetail.addCell(space);
			tableDetail.addCell(totalValue);

			document.add(table);
			document.add(tableDetail);

			document.close();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment; filename = " + data.getTransactionNumber() + "." + extension);

			OutputStream outputStream = response.getOutputStream();
			byteArrayOutputStream.writeTo(outputStream);
			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
