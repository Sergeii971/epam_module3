package com.epam.esm.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The type LocalDateTimeDeserializer.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class LocalDateTimeDeserializer  extends JsonDeserializer<LocalDateTime> {
    private final Logger logger = LogManager.getLogger(LocalDateTimeDeserializer.class);

    /**
     * deserialize.
     *
     * @return the LocalDateTime
     */
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) {
        LocalDateTime time = LocalDateTime.now();
        try {
            String dateStringFormat = p.getText();
            time = LocalDateTime.parse(dateStringFormat, LocalDateTimeSerializer.DATE_FORMATTER);
        } catch (DateTimeParseException | IOException e) {
            logger.log(Level.ERROR, "error while deserialize date", e);
        }
        return time;
    }
}
