<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/SalesSummary">
    <html>
    <head>
        <title>Mobile Phone Sales Report</title>
        <style>
            body { font-family: Arial, sans-serif; }
            h2 { text-align: center; color: #333; }
            table { width: 80%; margin: 20px auto; border-collapse: collapse; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
            th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
            th { background-color: #0099cc; color: white; font-weight: bold; }
            tr:nth-child(even) { background-color: #f2f2f2; }
            .red-sold { 
                color: white; 
                background-color: red; 
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <h2>Mobile Phone Sales Summary</h2>
        
        <table>
            <thead>
                <tr>
                    <th>ModelID</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Color</th>
                    <th>SIM Size</th>
                    <th>Memory</th>
                    <th>Camera</th>
                    <th>Touch Screen</th>
                    <th>Number Sold</th>
                    <th>Store Name</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="MobileSale">
                    <tr>
                        <td><xsl:value-of select="ModelID"/></td>
                        <td><xsl:value-of select="Brand"/></td>
                        <td><xsl:value-of select="Price"/></td>
                        <td><xsl:value-of select="Color"/></td>
                        <td><xsl:value-of select="SIMSize"/></td>
                        <td><xsl:value-of select="Memory"/></td>
                        <td><xsl:value-of select="Camera"/></td>
                        <td><xsl:value-of select="TouchScreen"/></td>
                        
                        <xsl:choose>
                            <xsl:when test="number(NoSold) &gt; 10">
                                <td class="red-sold">
                                    <xsl:value-of select="NoSold"/>
                                </td>
                            </xsl:when>
                            <xsl:otherwise>
                                <td>
                                    <xsl:value-of select="NoSold"/>
                                </td>
                            </xsl:otherwise>
                        </xsl:choose>
                        
                        <td><xsl:value-of select="storeName"/></td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </body>
    </html>
</xsl:template>

</xsl:stylesheet>
