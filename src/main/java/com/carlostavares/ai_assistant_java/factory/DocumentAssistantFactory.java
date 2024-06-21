package com.carlostavares.ai_assistant_java.factory;

import com.carlostavares.ai_assistant_java.utils.DocumentService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;

public class DocumentAssistantFactory {
    private DocumentService documentService;

    public DocumentAssistantFactory(ChatLanguageModel chatModel, ContentRetriever contentRetriever) {
        documentService = buildDocumentAiService(chatModel, contentRetriever);
    }

    public String chat(String message) {
        return documentService.chat(message);
    }

    private DocumentService buildDocumentAiService(ChatLanguageModel chatModel, ContentRetriever contentRetriever) {
        return AiServices.builder(DocumentService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }
}
