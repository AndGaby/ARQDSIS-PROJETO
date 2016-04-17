package br.com.usjt.controller;


import java.awt.List;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.usjt.model.Conta;

/**
 * Servlet implementation class ManterClienteController
 */
@WebServlet("/extrato")
public class ExtratoCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/conteudo/extrato.jsp");

		String parData = request.getParameter("data");
		String parDataFim = request.getParameter("dataFim");

		request.setAttribute("conta", Conta.conta);
		request.setAttribute("dataAtual", LocalDate.now());

		if (parData != null) {

			LocalDate inicial = LocalDate.parse(parData);
			LocalDate fim;

			if(parDataFim == null)
				fim = LocalDate.now();
			else
				fim = LocalDate.parse(parDataFim);

			//			extrato = new Extrato(ContaLogada.conta, inicial, fim);
			//			extrato.recuperarTodosRegistrosDeOperacoes();
			//			List<RegistroDeOperacao> listaRegistro = extrato.getListaRegistro();

			//			request.setAttribute("registros", listaRegistro);
		}

		dispatcher.forward(request, response);

	}	
}