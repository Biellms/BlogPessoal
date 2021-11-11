package br.org.generation.blogpessoal.security;

/*
 * Classe BasicSecurityConfig
 * 
 * Esta classe é responsável por habilitar a segurança básica da aplicação e o login
 * na aplicação.
 * 
 * Para habilitar a segurança HTTP no Spring, é necessario extender (herdar) 
 * a Classe WebSecurityConfigurerAdapter para fornecer uma configuração padrão 
 * no método configure (HttpSecurity http)
 * 
 * A configuração padrão garante que qualquer requisição enviada para a API 
 * seja autenticada com login baseado em formulário ou autenticação via Browser.
 * 
 * Para personalizar a autenticação é utilizada a sobrecarga dos métodos da Classe WebSecurityConfigurerAdapter
 * 
 * A annotation @EnableWebSecurity: habilita a configuração de segurança padrão do Spring Security na nossa api.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired // Injeção de Dependencia
	private UserDetailsService userDetailsService;
	
	@Override // Recupera os dados dos usuários gravados no Banco de dados.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
		auth.inMemoryAuthentication()
			.withUser("root")
			.password(passwordEncoder().encode("root"))
			.authorities("ROLE_USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* antMatchers().permitAll -> Endpoint liberado de autenticação
	 * 
	 * HttpMethod.OPTIONS -> O parâmetro HttpMethod.OPTIONS permite que 
	 * o cliente (frontend), possa descobrir quais são as opções de 
	 * requisição permitidas para um determinado recurso em um servidor. 
	 * Nesta implementação, está sendo liberada todas as opções das 
	 * requisições através do método permitAll().
	 * 
	 * anyRequest().authenticated() -> Todos os demais endpoints 
	 * serão autenticados
	 * 
	 * httpBasic -> Tipo de autenticação http (Basic Security)
	 * 
	 * Http Sessions: As sessões HTTP são um recurso que permite 
	 * que os servidores da Web mantenham a identidade do usuário 
	 * e armazenem dados específicos do usuário durante várias 
	 * interações (request/response) entre um aplicativo 
	 * cliente e um aplicativo da Web.
	 * 
	 * sessionManagement() -> Cria um gerenciador de Sessões
	 * 
	 * sessionCreationPolicy(SessionCreationPolicy.STATELESS) -> Define
	 * como o Spring Secuiryt irá criar (ou não) as sessões
	 * 
	 * STATELESS -> Nunca será criada uma sessão, ou seja, basta enviar
	 * o token através do cabeçalho da requisição que a mesma será processada.
	 * 
	 * cors -> O compartilhamento de recursos de origem cruzada (CORS) surgiu 
	 * porquê os navegadores não permitem solicitações feitas por um domínio
	 * (endereço) diferente daquele de onde o site foi carregado. Desta forma o 
	 * Frontend da aplicação, por exemplo, obrigatoriamente teria que estar 
	 * no mesmo domínio que o Backend. Habilitando o CORS, o Spring desabilita 
	 * esta regra e permite conexões de outros domínios.
	 * 
	 * CSRF: O cross-site request forgery (falsificação de 
	 * solicitação entre sites), é um tipo de ataque no qual comandos não 
	 * autorizados são transmitidos a partir de um usuário em quem a 
	 * aplicação web confia. 
	 * 
	 * csrf().disabled() -> Esta opção de proteção é habilitada por padrão no 
	 * Spring Security, entretanto precisamos desabilitar, caso contrário, todos 
	 * os endpoints que respondem ao verbo POST não serão executados.*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
	
}
