package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.CommandHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.QueryHandler;

@Component
public class Mediator {

    private final ApplicationContext applicationContext;

    
    public Mediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <TCommand extends Command> void dispatch(TCommand command) {
        Map<String, CommandHandler> commandHandlers = applicationContext.getBeansOfType(CommandHandler.class);
        CommandHandler<TCommand> handler = (CommandHandler<TCommand>) commandHandlers.values().stream().filter((h) -> h.supportedCommandType().equals(command.getClass())).findAny().get();
        if (handler != null) {
            handler.handle(command);
        } else {
            throw new IllegalArgumentException("No handler registered for command type: " + command.getClass());
        }
    }

    public <TQuery extends Query<TResult>, TResult> TResult dispatch(TQuery query) {
        Map<String, QueryHandler> queryHandlers = applicationContext.getBeansOfType(QueryHandler.class);
        QueryHandler<TQuery, TResult> handler = (QueryHandler<TQuery, TResult>) queryHandlers.values().stream().filter((q) -> q.supportedQueryType().equals(query.getClass())).findAny().get();;
        if (handler != null) {
            return handler.handle(query);
        } else {
            throw new IllegalArgumentException("No handler registered for query type: " + query.getClass());
        }
    }
}
