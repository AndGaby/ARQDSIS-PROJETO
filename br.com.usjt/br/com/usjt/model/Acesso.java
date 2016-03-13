package br.com.usjt.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.usjt.Cryptograrfia.LerConta;
import br.com.usjt.view.TelaEntrarComCodigo;
import br.com.usjt.view.TelaGerarCodigo;


public class Acesso  extends Observable{

	private int agencia, conta, senha, codigoDeAcesso;
	private boolean validar;
	private ResourceBundle idioma;

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
		setChanged();
		notifyObservers();
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
		setChanged();
		notifyObservers();
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
		setChanged();
		notifyObservers();
	}

	public int getCodigoDeAcesso() {
		return codigoDeAcesso;		
	}

	public void setCodigoDeAcesso(int codigoDeAcesso) {
		this.codigoDeAcesso = codigoDeAcesso;
		setChanged();
		notifyObservers();
	}

	public ResourceBundle getIdioma() {
		return idioma;
	}

	public void setIdioma(ResourceBundle idioma) {
		this.idioma = idioma;
	}

	public boolean validar(Conta conta) throws IOException, Exception{
		
		LerConta lerConta = new LerConta();
		BufferedReader reader = new BufferedReader(new FileReader(conta.getNumConta()+""));
		List<Conta> listaConta = new ArrayList<Conta>();
		String linha;

		while ((linha = reader.readLine()) != null) {
			listaConta.add(lerConta.descriptografar(linha));
		}
		for (Conta conta2 : listaConta) {
			if(conta2.getAgencia() == getAgencia() && conta2.getSenha() == getSenha()){		
				validar = true;
				if(conta2.getCodAcesso() == 0){
					validar = false;
					String codigoGerado =""; 
					for(int x = 0; x<3; x++){
						int i = (int)(0+ (Math.random()*9));
						codigoGerado += i;
					}
						conta.setCodAcesso(Integer.parseInt(codigoGerado));
						TelaGerarCodigo telaG = new TelaGerarCodigo(conta);
						telaG.setNumConta(conta2.getNumConta());
						lerConta.incluirConta(conta);
						try{//verifica se nenhum idioma foi selecionado
							telaG.internacionalizar(getIdioma());
						}catch(NullPointerException e){//se nenhum idioma for selecionado ele começa com padrão pelo português
							telaG.internacionalizar(ResourceBundle.getBundle("projeto", new Locale("pt", "BR")));
						}
						telaG.setAgencia(conta2.getAgencia());
						telaG.setSize(800, 400);
						telaG.setVisible(true);
				}else{
					TelaEntrarComCodigo entCod = new TelaEntrarComCodigo(conta2);
					try{//verifica se nenhum idioma foi selecionado
						entCod.internacionalizar(getIdioma());
					}catch(NullPointerException e){//se nenhum idioma for selecionado ele começa com padrão pelo português
						entCod.internacionalizar(ResourceBundle.getBundle("projeto", new Locale("pt", "BR")));
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "Verifique as informações digitadas!");
			}
		}
		reader.close();
		return validar;
	}
}