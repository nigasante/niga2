# Runtime image (ASP.NET Core 9)
FROM mcr.microsoft.com/dotnet/aspnet:9.0 AS base
WORKDIR /app
EXPOSE 80

# Build image
FROM mcr.microsoft.com/dotnet/sdk:9.0 AS build
WORKDIR /src
COPY ["thayhuumvc.csproj", "src/"]
RUN dotnet restore "src/thayhuumvc.csproj"
COPY . .
WORKDIR /src
RUN dotnet build "thayhuumvc.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "thayhuumvc.csproj" -c Release -o /app/publish /p:UseAppHost=false

# Final runtime container
FROM base AS final
WORKDIR /app    
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "thayhuumvc.dll"]