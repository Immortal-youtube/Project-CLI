
import discord
from dotenv import *

load_dotenv("token.env")


client = discord.Client(intents = discord.Intents.all())

@client.event
async def on_ready():
    print(f"{client.user} is now running")
    await client.change_presence(status=discord.Status.dnd)


client.run(get_key("TOKEN"))