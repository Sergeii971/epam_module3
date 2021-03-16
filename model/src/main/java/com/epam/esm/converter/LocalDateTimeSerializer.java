package com.epam.esm.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type LocalDateTimeSerializer.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Logger logger = LogManager.getLogger(LocalDateTimeSerializer.class);

    /**
     * serialize.
     *
     */
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) {
        try {
            String dateStringFormat = value.format(DATE_FORMATTER);
            gen.writeString(dateStringFormat);
        } catch (DateTimeParseException | IOException e) {
            logger.log(Level.ERROR, "error while serialize date", e);
        }
    }
}
