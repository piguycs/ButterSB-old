package com.thepiguy.learnkt.config.utils

import java.util.regex.Matcher
import java.util.regex.Pattern


class ParseActionBar {
    // §c120/120❤     §a10§a❈ Defense     §b100/100✎ Mana

    private val HealthParser : Pattern = Pattern.compile("(?<health>[0-9]+)/(?<maxHealth>[0-9]+)❤(?<wand>\\\\+(?<wandHeal>[0-9]+)[▆▅▄▃▂▁])?     (?<defense>[0-9]+)❈ Defense(?<other>( (?<align>\\\\|\\\\|\\\\|))?( {2}(?<tether>T[0-9]+!?))?.*)?     (?<num>[0-9,]+)/(?<den>[0-9,]+)✎(| Mana| (?<overflow>-?[0-9,]+)ʬ)")
    private val SkillParser: Pattern = Pattern.compile("\\+(?<gained>[0-9,.]+) (?<skillName>[A-Za-z]+) (?<progress>\\((((?<current>[0-9.,kM]+)/(?<total>[0-9.,kM]+))|((?<percent>[0-9.,]+)%))\\))")
    private val CollectionParser : Pattern = Pattern.compile("\\\\+(?<gained>[0-9,.]+) (?<skillName>[A-Za-z]+) (?<progress>\\\\((((?<current>[0-9.,kM]+)/(?<total>[0-9.,kM]+))|((?<percent>[0-9.,]+)%))\\\\))\"")
    private val AbilityParser: Pattern = Pattern.compile("(?<num>[0-9,]+)/(?<den>[0-9,]+)✎(| Mana| (?<overflow>-?[0-9,]+)ʬ)")

    fun statsParser(overlayMessage: String): List<String>? {
        val nocolorMessage = overlayMessage.replace("§.".toRegex(), "")
        val normalMatch : Matcher = HealthParser.matcher(nocolorMessage)

        return if (normalMatch.matches()) {
            val health = normalMatch.group("health")
            val maxHealth = normalMatch.group("maxHealth")
            val mana = normalMatch.group("num")
            val maxMana = normalMatch.group("den")

            listOf(health, maxHealth, mana, maxMana)

        } else {
            null
        }
    }

}