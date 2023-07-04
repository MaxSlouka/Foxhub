package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    List<String> spokenLanguages() {
        return List.of(
        "Italian", "Persian (Farsi)", "Thai", "Gujarati", "Polish", "Ukrainian", "Malayalam", "Kannada", "Oriya (Odia)",
                "Burmese", "Hakka", "Bhojpuri", "Tagalog", "Yoruba", "Maithili", "Uzbek", "Sindhi", "Amharic", "Fula",
                "Romanian", "Oromo", "Igbo", "Azerbaijani", "Dutch", "Kurdish", "Malagasy", "Saraiki", "Nepali", "Sinhala",
                "Czech", "Haitian Creole", "Hungarian", "Swedish", "Belarusian", "Shona", "Zulu", "Somali", "Tigrinya",
                "Kinyarwanda", "Hmong", "Uighur", "Greek", "Czech", "Bulgarian", "Khmer (Cambodian)", "Kazakh", "Danish",
                "Maltese", "Finnish", "Icelandic", "Slovak", "Lithuanian", "Slovenian", "Latvian", "Estonian", "Georgian",
                "Hausa", "Macedonian", "Albanian", "Afrikaans", "Swahili", "Mongolian", "Armenian", "Hebrew", "Yiddish",
                "Gujarati", "Assamese", "Kashmiri", "Konkani", "Maithili", "Manipuri", "Sindhi", "Tibetan", "Tulu", "Santali",
                "Awadhi", "Magahi", "Chhattisgarhi", "Malvi", "Bhili");

    }

}
