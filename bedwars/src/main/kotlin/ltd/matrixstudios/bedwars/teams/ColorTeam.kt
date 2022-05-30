package ltd.matrixstudios.bedwars.teams

enum class ColorTeam(
    var displayName: String,
    var displaySidebar: String,
    var normalColor: String
) {
    BLUE("Blue", "&9&lB", "&9"),
    RED("Red", "&c&lR", "&c"),
    GREEN("Green", "&a&lG", "&a"),
    YELLOW("Yellow", "&e&lY", "&e")
}