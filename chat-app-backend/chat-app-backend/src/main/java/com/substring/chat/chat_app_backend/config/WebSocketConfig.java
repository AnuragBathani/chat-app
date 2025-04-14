package com.substring.chat.chat_app_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/chat")
               .setAllowedOrigins("http://localhost:5173")
               .withSockJS();

       // /caht upper connection established thase
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // to get notified the subscriber
        //je subscriber e aa topic subscribe karelo hase tene jyre server pn koy new message avse tyre client ma push karidese
        config.setApplicationDestinationPrefixes("/app");
        // jyre /app prefix ma new message avse atle process karine /topic ma forward kari dese in real time
    }
}
