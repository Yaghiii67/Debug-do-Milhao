CREATE DATABASE DebugdoMilhaoBanco;
USE DebugdoMilhaoBanco;

CREATE TABLE temas (
id_tema INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(255)
);

CREATE TABLE perguntas (
id_pergunta INT AUTO_INCREMENT PRIMARY KEY,
id_tema INT NOT NULL,
enunciado VARCHAR(500) NOT NULL,
dificuldade ENUM('Facil', 'Medio', 'Dificil') DEFAULT 'Facil',
FOREIGN KEY (id_tema) REFERENCES temas(id_tema)
);

CREATE TABLE alternativas (
id_alternativa INT AUTO_INCREMENT PRIMARY KEY,
id_pergunta INT NOT NULL,
letra CHAR(1) NOT NULL,
texto VARCHAR(255) NOT NULL,
correta BOOLEAN DEFAULT FALSE,
explicacao VARCHAR(500),
FOREIGN KEY (id_pergunta) REFERENCES perguntas(id_pergunta)
);

CREATE TABLE jogadores (
id_jogador INT AUTO_INCREMENT PRIMARY KEY,
pontuacao INT DEFAULT 0
);

INSERT INTO temas (nome, descricao) VALUES
('Classes e Objetos', 'Criação de classes, atributos e instanciação de objetos'),
('Encapsulamento', 'Modificadores de acesso, getters e setters'),
('Herança',  'Herança entre classes e reaproveitamento de código'),
('Polimorfismo', 'Sobrescrita e sobrecarga de métodos'),
('Abstração', 'Classes abstratas e interfaces'),
('Exceções', 'Try-catch e tratamento de erros');

INSERT INTO perguntas (id_tema, enunciado, dificuldade) VALUES
(1, 'O que é uma classe em POO?', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(1, 'A', 'Um objeto já criado na memória', FALSE, 'Errada: um objeto já existente na memória é uma instância, não a classe. A classe é o molde usado para criar essa instância.'),
(1, 'B', 'Um molde para criar objetos', TRUE, 'Correta: a classe define atributos e métodos que servem de modelo para a criação (instanciação) de objetos.'),
(1, 'C', 'Uma função que retorna valores', FALSE, 'Errada: isso descreve um método, não uma classe. A classe pode conter métodos, mas não é um deles.'),
(1, 'D', 'Uma variável global do sistema', FALSE, 'Errada: uma variável armazena um único valor; a classe descreve uma estrutura inteira com atributos e comportamentos.');

INSERT INTO perguntas (id_tema, enunciado, dificuldade) VALUES
(2, 'Como se cria um objeto em Java?', 'Facil');
 
INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(2, 'A', 'Usando a palavra-chave "create"', FALSE, 'Errada: "create" não existe como palavra reservada em Java para instanciar objetos.'),
(2, 'B', 'Usando a palavra-chave "object"', FALSE, 'Errada: "object" não é uma palavra-chave da linguagem Java usada para instanciação.'),
(2, 'C', 'Usando a palavra-chave "new"', TRUE, 'Correta: "new" aloca memória no heap e chama o construtor da classe, criando a instância.'),
(2, 'D', 'Usando a palavra-chave "init"', FALSE, 'Errada: "init" não é palavra reservada em Java; a inicialização é feita pelo construtor, chamado via "new".');

INSERT INTO perguntas (id_tema, enunciado, dificuldade) VALUES
(3, 'Qual modificador torna um atributo acessível apenas dentro da própria classe?', 'Medio');
 
INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(3, 'A', 'public', FALSE, 'Errada: public permite acesso de qualquer classe, sem nenhuma restrição.'),
(3, 'B', 'protected', FALSE, 'Errada: protected permite acesso do mesmo pacote e de subclasses, indo além da própria classe.'),
(3, 'C', 'private', TRUE, 'Correta: private restringe o acesso exclusivamente ao código dentro da própria classe.'),
(3, 'D', 'static', FALSE, 'Errada: static indica que o membro pertence à classe (não à instância); não é um modificador de acesso.');

INSERT INTO perguntas (id_tema, enunciado, dificuldade) VALUES
(4, 'Qual palavra-chave é usada para herdar uma classe em Java?', 'Facil');
 
INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(4, 'A', 'implements', FALSE, 'Errada: implements é usado para uma classe implementar uma interface, não para herdar de outra classe.'),
(4, 'B', 'extends', TRUE, 'Correta: extends indica que uma classe herda atributos e métodos de outra (superclasse).'),
(4, 'C', 'inherits', FALSE, 'Errada: "inherits" não existe como palavra reservada em Java.'),
(4, 'D', 'super', FALSE, 'Errada: super é usado dentro da subclasse para acessar membros da superclasse, não para declarar a herança.');

INSERT INTO perguntas (id_tema, enunciado, dificuldade) VALUES
(5, 'O que é sobrescrita de método (override)?', 'Medio');
 
INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(5, 'A', 'Criar dois métodos com o mesmo nome e parâmetros diferentes', FALSE, 'Errada: isso descreve sobrecarga (overloading), um conceito diferente do override.'),
(5, 'B', 'Apagar o método da classe pai', FALSE, 'Errada: o método da classe pai continua existindo; o override apenas fornece uma nova implementação na classe filha.'),
(5, 'C', 'Reescrever na classe filha um método que existe na classe pai', TRUE, 'Correta: override é a redefinição, na subclasse, de um método já existente na superclasse, mantendo a mesma assinatura.'),
(5, 'D', 'Transformar um método em estático', FALSE, 'Errada: isso não tem relação com override; métodos estáticos, inclusive, não podem ser sobrescritos da mesma forma.');

INSERT INTO perguntas (id_tema, enunciado, dificuldade) VALUES
(6, 'Uma classe abstrata pode ser instanciada diretamente?', 'Medio');
 
INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(6, 'A', 'Sim, normalmente', FALSE, 'Errada: classes abstratas nunca podem ser instanciadas diretamente, independentemente do caso.'),
(6, 'B', 'Sim, usando "new abstract"', FALSE, 'Errada: essa sintaxe não existe em Java; "new" não instancia classes abstratas.'),
(6, 'C', 'Não, ela precisa ser herdada', TRUE, 'Correta: classes abstratas servem de base para outras classes; só uma subclasse concreta pode ser instanciada.'),
(6, 'D', 'Somente se não tiver métodos abstratos', FALSE, 'Errada: mesmo sem métodos abstratos, uma classe declarada como abstract não pode ser instanciada.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(7, 1, 'O que é um objeto?', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(7, 'A', 'Cópia de banco de dados.', FALSE, 'Errada: um objeto não tem relação direta com cópias de banco de dados.'),
(7, 'B', 'Instância real de uma classe.', TRUE, 'Correta: o objeto é a materialização em memória de uma classe, com seus atributos e comportamentos definidos.'),
(7, 'C', 'Tipo de dado primitivo.', FALSE, 'Errada: tipos primitivos (int, boolean, etc.) não são objetos e não seguem o modelo de classes.'),
(7, 'D', 'Uma função matemática simples.', FALSE, 'Errada: isso não descreve o conceito de objeto em POO.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(8, 1, 'Para que serve o Construtor?', 'Medio');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(8, 'A', 'Deletar objeto da memória.', FALSE, 'Errada: quem remove objetos da memória é o garbage collector, não o construtor.'),
(8, 'B', 'Inicializar atributos do objeto.', TRUE, 'Correta: o construtor é executado na criação do objeto para definir o estado inicial de seus atributos.'),
(8, 'C', 'Criptografar dados da classe.', FALSE, 'Errada: criptografia não é uma função do construtor.'),
(8, 'D', 'Copiar uma classe existente.', FALSE, 'Errada: copiar uma classe seria função de um construtor de cópia específico, não do construtor em geral.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(9, 2, 'Qual modificador oculta o atributo?', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(9, 'A', 'public', FALSE, 'Errada: public expõe o atributo, ao invés de ocultá-lo.'),
(9, 'B', 'protected', FALSE, 'Errada: protected ainda permite acesso a subclasses e ao mesmo pacote, não oculta totalmente.'),
(9, 'C', 'private', TRUE, 'Correta: private oculta o atributo, tornando-o acessível apenas dentro da própria classe.'),
(9, 'D', 'static', FALSE, 'Errada: static define que o membro pertence à classe, não altera sua visibilidade.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(10, 2, 'O que o protected acessa?', 'Medio');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(10, 'A', 'Apenas a própria classe.', FALSE, 'Errada: essa restrição descreve o modificador private, não o protected.'),
(10, 'B', 'Classes sem nenhuma herança.', FALSE, 'Errada: protected é justamente pensado para funcionar bem com herança, não o contrário.'),
(10, 'C', 'Mesmo pacote e subclasses.', TRUE, 'Correta: protected libera acesso para classes do mesmo pacote e para subclasses, mesmo em pacotes diferentes.'),
(10, 'D', 'Todo o sistema livremente.', FALSE, 'Errada: acesso livre a todo o sistema é característica do modificador public.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(11, 3, 'Qual objetivo principal da Herança?', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(11, 'A', 'Bloquear acesso aos atributos.', FALSE, 'Errada: bloquear acesso é papel do encapsulamento, não da herança.'),
(11, 'B', 'Permitir reaproveitamento de código.', TRUE, 'Correta: a herança permite que uma subclasse reaproveite atributos e métodos já definidos na superclasse.'),
(11, 'C', 'Isolar banco de dados.', FALSE, 'Errada: herança é um conceito de POO e não tem relação com isolamento de banco de dados.'),
(11, 'D', 'Criar loops automáticos.', FALSE, 'Errada: laços de repetição são estruturas de controle de fluxo, não relacionadas à herança.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(12, 3, 'O Problema do Diamante causa:', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(12, 'A', 'Estouro de memória RAM.', FALSE, 'Errada: o problema do diamante é um conflito de herança, não um problema de consumo de memória.'),
(12, 'B', 'Ambiguidade na herança múltipla.', TRUE, 'Correta: quando duas superclasses possuem um método com mesma assinatura herdado de uma ancestral comum, fica ambíguo qual versão usar.'),
(12, 'C', 'Perda de dados salvos.', FALSE, 'Errada: não há relação com persistência ou perda de dados.'),
(12, 'D', 'Bloqueio de construtores filhos.', FALSE, 'Errada: o problema está na resolução de métodos herdados, não em bloqueio de construtores.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(13, 4, 'O que é Sobrecarga (Overloading)?', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(13, 'A', 'Mesmo nome, parâmetros diferentes.', TRUE, 'Correta: overloading permite vários métodos com o mesmo nome na mesma classe, desde que a lista de parâmetros seja diferente.'),
(13, 'B', 'Substituir método da mãe.', FALSE, 'Errada: isso descreve override (sobrescrita), não sobrecarga.'),
(13, 'C', 'Nomes diferentes, mesma função.', FALSE, 'Errada: sobrecarga exige o mesmo nome de método, não nomes diferentes.'),
(13, 'D', 'Apagar métodos da memória.', FALSE, 'Errada: sobrecarga não remove métodos, apenas adiciona variações do mesmo nome.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(14, 4, 'O que é Sobrescrita (Override)?', 'Medio');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(14, 'A', 'Mesmo nome na mesma classe.', FALSE, 'Errada: isso descreve sobrecarga (overloading), não sobrescrita.'),
(14, 'B', 'Redefinir método da superclasse.', TRUE, 'Correta: override é a redefinição, na subclasse, de um método já existente na superclasse, com a mesma assinatura.'),
(14, 'C', 'Bloquear método com senha.', FALSE, 'Errada: não existe esse conceito de bloqueio por senha em override.'),
(14, 'D', 'Importar métodos de fora.', FALSE, 'Errada: override não envolve importação de código externo à hierarquia de classes.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(15, 5, 'Classe abstrata serve para:', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(15, 'A', 'Impedir qualquer herança futura.', FALSE, 'Errada: é o contrário; a classe abstrata existe justamente para ser herdada.'),
(15, 'B', 'Armazenar apenas dados estáticos.', FALSE, 'Errada: classes abstratas podem ter atributos e métodos de instância normalmente, não só membros estáticos.'),
(15, 'C', 'Modelo conceitual não instanciável.', TRUE, 'Correta: a classe abstrata define um modelo comum a ser especializado pelas subclasses, mas não pode ser instanciada diretamente.'),
(15, 'D', 'Apagar dados do programa.', FALSE, 'Errada: não há relação entre classe abstrata e exclusão de dados.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(16, 5, 'O que define uma Interface?', 'Medio');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(16, 'A', 'Contrato de métodos obrigatórios.', TRUE, 'Correta: a interface define um conjunto de métodos que as classes implementadoras são obrigadas a fornecer.'),
(16, 'B', 'Classe que guarda dados.', FALSE, 'Errada: interfaces tradicionalmente não guardam estado (atributos de instância), apenas assinaturas de métodos.'),
(16, 'C', 'Tela visual do sistema.', FALSE, 'Errada: interface, no contexto de POO, não se refere à interface gráfica do usuário.'),
(16, 'D', 'Variável para números inteiros.', FALSE, 'Errada: isso descreve um tipo de dado, sem relação com o conceito de interface em POO.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(17, 6, 'Qual função do try-catch?', 'Facil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(17, 'A', 'Melhorar velocidade dos loops.', FALSE, 'Errada: try-catch trata exceções e não tem relação com desempenho de laços.'),
(17, 'B', 'Tratar erros evitando travamentos.', TRUE, 'Correta: try-catch captura exceções lançadas durante a execução, permitindo tratá-las sem interromper o programa abruptamente.'),
(17, 'C', 'Ocultar o código fonte.', FALSE, 'Errada: try-catch não tem função de ocultação de código.'),
(17, 'D', 'Reiniciar o computador sozinho.', FALSE, 'Errada: não existe essa função para try-catch em nenhuma linguagem de programação.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(18, 6, 'Quando o finally executa?', 'Medio');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(18, 'A', 'Sempre, com erro ou não.', TRUE, 'Correta: o bloco finally é executado sempre, tenha ocorrido exceção ou não, garantindo por exemplo o fechamento de recursos.'),
(18, 'B', 'Apenas se não houver erro.', FALSE, 'Errada: finally executa mesmo quando o catch trata um erro.'),
(18, 'C', 'Quando o sistema desliga.', FALSE, 'Errada: finally está associado ao fluxo do bloco try-catch, não a eventos do sistema operacional.'),
(18, 'D', 'Se o catch falhar.', FALSE, 'Errada: finally não depende do sucesso ou falha do catch; ele roda de qualquer forma.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(19, 1, 'Onde objetos instanciados ficam armazenados?', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(19, 'A', 'Na memória Stack.', FALSE, 'Errada: a Stack guarda variáveis locais e referências, não os objetos em si.'),
(19, 'B', 'Na memória Heap.', TRUE, 'Correta: objetos criados com "new" são alocados na Heap, enquanto a Stack guarda apenas as referências a eles.'),
(19, 'C', 'No disco rígido (HD).', FALSE, 'Errada: objetos em execução ficam na memória RAM, não diretamente no disco.'),
(19, 'D', 'No cache do processador.', FALSE, 'Errada: o cache do processador armazena dados temporários de baixo nível, não objetos de aplicação.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(20, 2, 'Retornar referência de lista privada:', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(20, 'A', 'Garante segurança máxima.', FALSE, 'Errada: é o oposto; expõe o objeto interno, permitindo alteração externa indevida.'),
(20, 'B', 'Quebra o encapsulamento.', TRUE, 'Correta: ao devolver a referência direta de um atributo privado, código externo pode modificá-lo livremente, violando o encapsulamento.'),
(20, 'C', 'Gera erro de compilação.', FALSE, 'Errada: isso compila normalmente; o problema é conceitual (design), não sintático.'),
(20, 'D', 'Aumenta performance do código.', FALSE, 'Errada: não há ganho de performance relevante nessa prática; o risco é de segurança/integridade dos dados.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(21, 3, 'Abusar de herança causa qual problema?', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(21, 'A', 'Acoplamento forte e rigidez.', TRUE, 'Correta: hierarquias de herança muito profundas ou mal planejadas criam forte dependência entre classes, dificultando mudanças.'),
(21, 'B', 'Erros de sintaxe obrigatórios.', FALSE, 'Errada: usar herança em excesso não gera erros de sintaxe automaticamente.'),
(21, 'C', 'Perda de polimorfismo dinâmico.', FALSE, 'Errada: o polimorfismo continua funcionando; o problema é de manutenção e acoplamento.'),
(21, 'D', 'Loops infinitos na execução.', FALSE, 'Errada: herança em excesso não causa loops infinitos por si só.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(22, 4, 'Ligação dinâmica (Dynamic Binding) decide:', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(22, 'A', 'Método pelo tipo da referência.', FALSE, 'Errada: decidir pelo tipo da referência é característica da ligação estática (static binding).'),
(22, 'B', 'Método pelo objeto real na execução.', TRUE, 'Correta: dynamic binding resolve, em tempo de execução, qual método usar com base no tipo real do objeto, permitindo polimorfismo.'),
(22, 'C', 'Erros em tempo de compilação.', FALSE, 'Errada: dynamic binding acontece em tempo de execução, não de compilação.'),
(22, 'D', 'Alocação de memória estática.', FALSE, 'Errada: isso não tem relação com o conceito de ligação dinâmica de métodos.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(23, 5, 'Por que programar para interfaces?', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(23, 'A', 'Aumenta linhas de código.', FALSE, 'Errada: esse não é o objetivo nem uma vantagem buscada; é apenas uma consequência colateral possível.'),
(23, 'B', 'Isola lógica e reduz acoplamento.', TRUE, 'Correta: programar para interfaces permite trocar implementações sem afetar o código cliente, reduzindo dependências diretas.'),
(23, 'C', 'Criptografa dados do sistema.', FALSE, 'Errada: interfaces não têm relação com criptografia de dados.'),
(23, 'D', 'Evita uso de memória.', FALSE, 'Errada: usar interfaces não elimina o uso de memória pelos objetos concretos criados.');

INSERT INTO perguntas (id_pergunta, id_tema, enunciado, dificuldade) VALUES
(24, 6, 'Exceções checadas (Checked) exigem o quê?', 'Dificil');

INSERT INTO alternativas (id_pergunta, letra, texto, correta, explicacao) VALUES
(24, 'A', 'Tratamento obrigatório na compilação.', TRUE, 'Correta: exceções checked precisam ser tratadas com try-catch ou declaradas com throws, sob pena de erro de compilação.'),
(24, 'B', 'Fechamento do sistema operacional.', FALSE, 'Errada: exceções checked não têm relação com desligar o sistema operacional.'),
(24, 'C', 'Uso de variáveis estáticas.', FALSE, 'Errada: não há exigência de uso de variáveis estáticas para tratar exceções checked.'),
(24, 'D', 'Reinício do banco de dados.', FALSE, 'Errada: não há relação entre exceções checked e reinício de banco de dados.');